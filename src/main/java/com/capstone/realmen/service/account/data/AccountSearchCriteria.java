package com.capstone.realmen.service.account.data;

import java.util.List;
import java.util.Objects;

import com.capstone.realmen.common.enums.EAccountStatus;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AccountSearchCriteria(
        String search, // first + last name, phone, staffCode
        Long branchId,
        List<String> roles,
        List<String> professionalTypeCodes,
        List<String> accountStatusCodes) {
    public List<String> defaultStatusCodes() {
        return EAccountStatus.defaultStatuses(accountStatusCodes);
    }

    public AccountSearchCriteria toLowerCase() {
        return AccountSearchCriteria.builder()
                .search(search.toLowerCase())
                .branchId(branchId)
                .roles(roles)
                .accountStatusCodes(accountStatusCodes)
                .professionalTypeCodes(professionalTypeCodes)
                .build();
    }

    public Boolean hasSearchEmpty() {
        return Objects.isNull(search) || search.isEmpty();
    }

    public Boolean hasBranchIdEmpty() {
        return Objects.isNull(branchId);
    }

    public Boolean hasStatusEmpty() {
        return Objects.isNull(accountStatusCodes) || accountStatusCodes.isEmpty();
    }

    public Boolean hasProfessionalTypeCodeEmpty() {
        return Objects.isNull(professionalTypeCodes)
                || professionalTypeCodes.isEmpty();
    }
}
