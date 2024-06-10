package com.capstone.realmen.service.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.capstone.realmen.common.enums.EAccountStatus;
import com.capstone.realmen.common.enums.ERole;
import com.capstone.realmen.common.request.RequestContext;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.account.AccountCreated;
import com.capstone.realmen.data.dto.account.AccountMapper;
import com.capstone.realmen.repository.database.account.AccountEntity;
import com.capstone.realmen.repository.database.account.AccountRepository;
import com.capstone.realmen.repository.database.audit.Auditable;
import com.capstone.realmen.service.account.data.CreateRequire;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountCommandService {
    @NonNull
    AccountRepository accountRepository;
    @NonNull
    AccountMapper accountMapper;
    @NonNull
    RequestContext requestContext;
    @NonNull
    PasswordEncoder passwordEncoder;

    @Value("${app.default.password}")
    String appDefaultPassword;
    @Value("${app.mobile.default.password}")
    String appMobDefaultPassword;

    public AccountCreated createAccount(CreateRequire createRequire) {
        if(createRequire.isForStaff()) {
            return this.createStaff(createRequire.account());
        } else {
            if(createRequire.isCreatedByRecept()) {
                return this.createCustomerByReceptionist(createRequire.account());
            } else {
                return this.createCustomer(createRequire.account());
            }
        }
    }

    private AccountCreated createStaff(Account account) {
        Account audit = requestContext.getAccount();
        AccountEntity newAccount = accountMapper.toEntity(account);

        switch (ERole.findByCode(audit.roleCode()).get()) {
            case BRANCH_MANAGER:
                accountRepository.save(
                        newAccount
                                .withPassword(passwordEncoder.encode(appDefaultPassword))
                                .withStatus(EAccountStatus.ACTIVE.getCode(),
                                        EAccountStatus.ACTIVE.getName())
                                .withAudit(Auditable.ofCreated(audit))

                );
                return AccountCreated.byManager(newAccount.getAccountId());
            case SHOP_OWNER:
                accountRepository.save(
                        newAccount
                                .withPassword(passwordEncoder.encode(appDefaultPassword))
                                .withStatus(EAccountStatus.PENDING_BRANCH.getCode(),
                                        EAccountStatus.PENDING_BRANCH.getName())
                                .withAudit(Auditable.ofCreated(audit))

                );
                return AccountCreated.byShopOwner(newAccount.getAccountId());
            default:
                return null;
        }
    }

    private AccountCreated createCustomer(Account account) {
        AccountEntity newAccount = accountMapper.toEntity(account);
        accountRepository.save(
                newAccount
                        .withPassword(appMobDefaultPassword)
                        .withStatus(EAccountStatus.ACTIVE.getCode(),
                                EAccountStatus.ACTIVE.getName())
                        .withAudit(Auditable.ofCreated(requestContext.getAccount()))
        );
        return AccountCreated.byDefault(newAccount.getAccountId());
    }

    private AccountCreated createCustomerByReceptionist(Account account) {
        AccountEntity newAccount = accountMapper.toEntity(account);
        accountRepository.save(
                newAccount
                        .withPassword(appMobDefaultPassword)
                        .withStatus(EAccountStatus.PENDING_ACTIVE.getCode(),
                                EAccountStatus.PENDING_ACTIVE.getName())
                        .withAudit(Auditable.ofCreated(requestContext.getAccount()))
        );
        return AccountCreated.byReceptionist(newAccount.getAccountId());
    }
}
