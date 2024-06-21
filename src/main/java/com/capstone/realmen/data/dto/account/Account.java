package com.capstone.realmen.data.dto.account;

import java.time.LocalDate;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record Account(
        Long accountId,
        Long branchId,
        String firstName,
        String lastName,
        String phone,
        String address,
        String password,
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
