package com.capstone.realmen.controller.api.admin.account.models;

import java.time.LocalDate;

import com.capstone.realmen.common.validate.Phone;
import com.capstone.realmen.controller.api.admin.account.models.enums.EGenderRequest;
import com.capstone.realmen.controller.api.admin.account.models.enums.ERoleRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AccountRequest(
    String firstName,
    String lastName,
    @Phone String phone,
    String address,
    String professionalTypeCode,
    String professionalTypeName,
    @JsonProperty("role") String roleCode,
    String roleName,
    String thumbnail,
    LocalDate dob,
    @JsonProperty("gender") String genderCode,
    String genderName
) {
    public AccountRequest withRole(ERoleRequest roleRequest) {
        return this.withRoleCode(roleRequest.getCode())
            .withRoleName(roleRequest.getName());
    }

    public AccountRequest withGender(EGenderRequest genderRequest) {
        return this.withRoleCode(genderRequest.getCode())
            .withRoleName(genderRequest.getName());
    }
    
}
