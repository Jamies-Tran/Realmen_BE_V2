package com.capstone.realmen.service.account.others.branch;

import org.springframework.stereotype.Service;

import com.capstone.realmen.common.enums.EWorkingStatus;
import com.capstone.realmen.data.dto.account.branch.IAccountBranchMapper;
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

    public void createList(AccountBranchCreateRequire createRequire) {
        accountBranchRepository.saveAll(
                createRequire.accountBranches()
                        .stream()
                        .map(accountBranch -> accountBranch
                                .withWorkingStatusCode(EWorkingStatus.WORKING.getCode())
                                .withWorkingStatusName(EWorkingStatus.WORKING.getName()))
                        .map(accountBranchMapper::toEntity)
                        .toList()

        );
    }
}
