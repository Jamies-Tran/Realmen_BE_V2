package com.capstone.realmen.service.branch;

import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.RequestContext;
import com.capstone.realmen.data.dto.branch.IBranchMapper;
import com.capstone.realmen.data.dto.branch.address.Address;
import com.capstone.realmen.repository.database.audit.Auditable;
import com.capstone.realmen.repository.database.branch.BranchEntity;
import com.capstone.realmen.repository.database.branch.IBranchRepository;
import com.capstone.realmen.service.branch.data.BranchCreateRequire;
import com.capstone.realmen.service.branch.others.address.AddressQueryService;
import com.capstone.realmen.service.branch.others.address.data.AddressSearchByField;
import com.capstone.realmen.service.branch.others.displays.BranchDisplayCommandService;
import com.capstone.realmen.service.branch.others.displays.data.BranchDisplayCreateRequire;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchCommandService {
    @NonNull
    IBranchRepository branchRepository;

    @NonNull
    AddressQueryService addressQueryService;

    @NonNull
    BranchDisplayCommandService branchDisplayCommandService;

    @NonNull
    IBranchMapper branchMapper;

    @NonNull
    RequestContext requestContext;

    public void create(BranchCreateRequire createRequire) {
        Address branchAddress = addressQueryService.getAddress(
            AddressSearchByField.builder()
                .address(createRequire.branch().branchAddress())
                .build()
        );
        BranchEntity newBranch = branchMapper.toEntity(createRequire.branch());
        BranchEntity saveBranch = branchRepository.save(
            newBranch
                .withAddress(branchAddress)
                .withAudit(Auditable.ofCreated(requestContext.getAccount()))
        );
        branchDisplayCommandService.create(
            BranchDisplayCreateRequire.builder()
                .branchId(saveBranch.getBranchId())
                .branchDisplays(createRequire.branchDisplays())
                .build()
        );
    }
}
