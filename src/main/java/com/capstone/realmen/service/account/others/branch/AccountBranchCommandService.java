package com.capstone.realmen.service.account.others.branch;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.realmen.common.enums.EWorkingStatus;
import com.capstone.realmen.data.dto.account.branch.IAccountBranchMapper;
import com.capstone.realmen.repository.database.account.branch.AccountBranchEntity;
import com.capstone.realmen.repository.database.account.branch.IAccountBranchRepository;
import com.capstone.realmen.service.account.others.branch.data.AccountBranchCreateRequire;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountBranchCommandService {
    @NonNull
    IAccountBranchRepository accountBranchRepository;
    @NonNull
    IAccountBranchMapper accountBranchMapper;

    public List<Long> createList(AccountBranchCreateRequire createRequire) {
        List<AccountBranchEntity> accountBranches = accountBranchRepository.saveAll(
                createRequire.accountBranches()
                        .stream()
                        .map(accountBranch -> accountBranch
                                .withWorkingStatusCode(EWorkingStatus.WORKING.getCode())
                                .withWorkingStatusName(EWorkingStatus.WORKING.getName()))
                        .map(accountBranchMapper::toEntity)
                        .toList()

        );

        return accountBranches.stream().map(AccountBranchEntity::getAccountId).toList();
    }
}
