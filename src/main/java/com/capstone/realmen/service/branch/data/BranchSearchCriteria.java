package com.capstone.realmen.service.branch.data;

import lombok.Builder;
import lombok.With;

import java.util.List;
import java.util.Objects;

import com.capstone.realmen.common.enums.EBranchStatus;
import com.capstone.realmen.data.dto.branch.status.BranchStatus;

@With
@Builder
public record BranchSearchCriteria(
        String search,
        Double latitude,
        Double longitude,
        List<String> branchStatusCodes) {
            
    public List<String> defaulStatusCodes() {
        return EBranchStatus.defaultStatuses(branchStatusCodes)
                .stream()
                .map(BranchStatus::code)
                .toList();
    }

    public BranchSearchCriteria toLowerCase() {
        return this.withSearch(search.toLowerCase());
    }

    public Boolean hasSearchEmpty() {
        return Objects.isNull(search) || search.isEmpty();
    }

    public Boolean hasBranchStatusCodeEmpty() {
        return Objects.isNull(branchStatusCodes) || branchStatusCodes.isEmpty();
    }
}
