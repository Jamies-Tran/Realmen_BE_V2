package com.capstone.realmen.controller.api.admin.account.models;

import java.time.LocalDate;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record AccountResponse(
        Long accountId,
        Long branchId,
        String firstName,
        String lastName,
        String phone,
        String address,
        String staffCode,
        String roleCode,
        String roleName,
        String professionalTypeCode,
        String professionalTypeName,
        String thumbnail,
        LocalDate dob,
        String genderCode,
        String genderName,
        String accountStatusCode,
        String accountStatusName) {

}
