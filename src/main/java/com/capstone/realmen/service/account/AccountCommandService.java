package com.capstone.realmen.service.account;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.capstone.realmen.common.enums.EAccountStatus;
import com.capstone.realmen.common.enums.ERole;
import com.capstone.realmen.common.request.RequestContext;
import com.capstone.realmen.controller.handler.exceptions.ConflicException;
import com.capstone.realmen.controller.handler.exceptions.InvalidRequest;
import com.capstone.realmen.controller.security.encoder.AppPasswordEncoder;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.account.AccountCreated;
import com.capstone.realmen.data.dto.account.IAccountMapper;
import com.capstone.realmen.repository.database.account.AccountEntity;
import com.capstone.realmen.repository.database.account.IAccountRepository;
import com.capstone.realmen.repository.database.audit.Auditable;
import com.capstone.realmen.service.account.data.AccountCreateRequire;
import com.capstone.realmen.service.account.helpers.AccountHelper;
import com.capstone.realmen.service.account.others.branch.AccountBranchCommandService;
import com.capstone.realmen.service.account.others.branch.AccountBranchQueryService;
import com.capstone.realmen.service.account.others.branch.data.AccountBranchCreateRequire;
import com.capstone.realmen.service.branch.others.services.BranchServiceCommandService;
import com.capstone.realmen.service.branch.others.services.data.BranchServiceActiveRequire;
import com.twilio.exception.InvalidRequestException;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountCommandService extends AccountHelper {
    @NonNull
    IAccountRepository accountRepository;

    @NonNull
    IAccountMapper accountMapper;

    @NonNull
    RequestContext requestContext;

    @NonNull
    AccountBranchCommandService accountBranchCommandService;

    @NonNull
    AccountBranchQueryService accountBranchQueryService;

    @NonNull
    BranchServiceCommandService branchServiceCommandService;

    @NonNull
    final AppPasswordEncoder appPasswordEncoder;

    @NonFinal
    @Value("${app.default.password}")
    String appDefaultPassword;

    @NonFinal
    @Value("${app.mobile.default.password}")
    String appMobDefaultPassword;

    public AccountCreated createAccount(AccountCreateRequire createRequire) {
        if (createRequire.isForStaff()) {
            return this.createStaff(createRequire.account());
        } else {
            if (accountRepository.existsByPhone(createRequire.account().phone())) {
                throw new ConflicException("Thông tin tài khoản đã tồn tại");
            }
            if (createRequire.isCreatedByRecept()) {
                return this.createCustomerByReceptionist(createRequire.account());
            } else {
                return this.createCustomer(createRequire.account());
            }
        }
    }

    public void active(List<Long> accountIds) {
        List<Account> accounts = accountRepository
                .findAllByIds(accountIds)
                .stream()
                .map(accountMapper::toDto)
                .toList();
        List<AccountEntity> newAccounts = accounts.stream()
                .map(account -> {
                    String staffCode = generateStaffCode(account);
                    return accountMapper.toEntity(account)
                            .withStaffCode(staffCode)
                            .withAccountStatusCode(EAccountStatus.ACTIVE.getCode())
                            .withAccountStatusName(EAccountStatus.ACTIVE.getName());
                })
                .toList();
        accountRepository.saveAll(newAccounts);
    }

    private AccountCreated createStaff(Account account) {
        if (!Objects.equals(account.roleCode(), ERole.OPERATOR_STAFF.getCode())
                && !Objects.equals(account.roleCode(), ERole.BRANCH_MANAGER.getCode())) {
            throw new InvalidRequest("Yêu cầu tạo tài khoản không hợp lệ");
        }

        if (Objects.equals(account.roleCode(), ERole.OPERATOR_STAFF.getCode())
                && !StringUtils.hasText(account.professionalTypeCode())) {
            throw new InvalidRequest("Thông tin nhân viên vận hành không hợp lệ");
        }

        if (accountRepository.existsByPhone(account.phone())) {
            throw new ConflicException("Thông tin tài khoản đã tồn tại");
        }

        PasswordEncoder passwordEncoder = appPasswordEncoder.passwordEncoder();
        Account audit = requestContext.getAccount();
        AccountEntity newAccount = accountMapper.toEntity(account);

        switch (ERole.findByCode(audit.roleCode()).get()) {
            case BRANCH_MANAGER:
                Long branchId = requestContext.getAccount().branchId();
                String staffCode = generateStaffCode(account.withBranchId(branchId));
                newAccount = accountRepository.save(
                        newAccount
                                .withStaffCode(staffCode)
                                .withRoleName(ERole
                                        .findByCode(account.roleCode()).get().getName())
                                .withPassword(passwordEncoder
                                        .encode(appDefaultPassword))
                                .withStatus(EAccountStatus.ACTIVE.getCode(),
                                        EAccountStatus.ACTIVE.getName())
                                .setAudit(Auditable.ofCreated(audit)));

                AccountBranchCreateRequire createRequire = AccountBranchCreateRequire
                        .of(branchId, List.of(accountMapper.toDto(newAccount)));
                accountBranchCommandService.createList(createRequire);

                BranchServiceActiveRequire bsActiveRequire = BranchServiceActiveRequire
                        .of(branchId, newAccount);
                branchServiceCommandService.activeBranchService(bsActiveRequire);

                return AccountCreated.byManager(newAccount.getAccountId());
            case SHOP_OWNER:
                newAccount = accountRepository.save(
                        newAccount
                                .withRoleName(ERole
                                        .findByCode(account.roleCode()).get().getName())
                                .withPassword(passwordEncoder.encode(appDefaultPassword))
                                .withStatus(EAccountStatus.PENDING_BRANCH.getCode(),
                                        EAccountStatus.PENDING_BRANCH.getName())
                                .setAudit(Auditable.ofCreated(audit))

                );
                return AccountCreated.byShopOwner(newAccount.getAccountId());
            default:
                return null;
        }
    }

    private AccountCreated createCustomer(Account account) {
        PasswordEncoder passwordEncoder = appPasswordEncoder.passwordEncoder();
        AccountEntity newAccount = accountMapper.toEntity(account);
        newAccount = accountRepository.save(
                newAccount
                        .withPassword(passwordEncoder.encode(appMobDefaultPassword))
                        .withStatus(EAccountStatus.ACTIVE.getCode(),
                                EAccountStatus.ACTIVE.getName())
                        .withRole(ERole.CUSTOMER.getCode(), ERole.CUSTOMER.getName())
                        .setAudit(Auditable.ofDefault()));
        return AccountCreated.byDefault(newAccount.getAccountId());
    }

    private AccountCreated createCustomerByReceptionist(Account account) {
        AccountEntity newAccount = accountMapper.toEntity(account);
        accountRepository.save(
                newAccount
                        .withPassword(appMobDefaultPassword)
                        .withStatus(EAccountStatus.PENDING_ACTIVE.getCode(),
                                EAccountStatus.PENDING_ACTIVE.getName())
                        .withRole(ERole.CUSTOMER.getCode(), ERole.CUSTOMER.getName()));
        return AccountCreated.byReceptionist(newAccount.getAccountId());
    }
}
