package com.capstone.realmen.controller.api.app.account.models;

import java.time.LocalDate;

import com.capstone.realmen.common.validate.Phone;
import com.capstone.realmen.controller.api.admin.account.models.enums.EGenderRequest;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AccountRequest(
        String firstName,
        String lastName,
        @Phone String phone,
        String thumbnail,
        LocalDate dob,
        String genderCode,
        String genderName) {
    public AccountRequest withGender(EGenderRequest genderRequest) {
        return this.withGenderCode(genderRequest.getCode()).withGenderName(genderRequest.getName());
    }
}
