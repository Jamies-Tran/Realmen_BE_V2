package com.capstone.realmen.service.account.others.branch;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.realmen.data.dto.account.branch.AccountBranch;
import com.capstone.realmen.data.dto.account.branch.IAccountBranchMapper;
import com.capstone.realmen.repository.database.account.branch.IAccountBranchRepository;
import com.capstone.realmen.service.account.others.branch.data.AccountBranchSearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountBranchQueryService {
    @NonNull
    IAccountBranchRepository repository;

    @NonNull
    IAccountBranchMapper mapper;

    public List<AccountBranch> findAllBy(AccountBranchSearchByField searchByField) {
        return repository.findAllByBranchId(searchByField.branchId())
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
