package com.capstone.realmen.service.branch.others.displays;

import org.springframework.stereotype.Service;

import com.capstone.realmen.data.dto.branch.display.IBranchDisplayMapper;
import com.capstone.realmen.repository.database.branch.display.IBranchDisplayRepository;
import com.capstone.realmen.service.branch.others.displays.data.BranchDisplayCreateRequire;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchDisplayCommandService {
    @NonNull
    IBranchDisplayRepository branchDisplayRepository;
    @NonNull
    IBranchDisplayMapper branchDisplayMapper;

    public void create(BranchDisplayCreateRequire createRequire) {
        branchDisplayRepository.saveAll(
            createRequire.branchDisplays()
                .stream()
                .map(branchDisplayMapper::toEntity)
                .toList()
        );
    }
}
