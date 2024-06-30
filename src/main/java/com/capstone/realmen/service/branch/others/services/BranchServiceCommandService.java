package com.capstone.realmen.service.branch.others.services;

import org.springframework.stereotype.Service;

import com.capstone.realmen.data.dto.branch.service.IBranchServiceMapper;
import com.capstone.realmen.repository.database.branch.service.IBranchServiceRepository;
import com.capstone.realmen.service.branch.others.services.data.BranchServiceCreateRequire;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchServiceCommandService {
    @NonNull
    IBranchServiceRepository branchServiceRepository;

    @NonNull
    IBranchServiceMapper branchServiceMapper;

    public void createList(BranchServiceCreateRequire createRequire) {
        branchServiceRepository.saveAll(createRequire.branchServices()
            .stream()
            .map(branchServiceMapper::toEntity)
            .toList());
    }
}
