package com.capstone.realmen.controller.api.admin.account.models.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EGenderRequest {
    MALE("MALE", "Nam"),
    FEMALE("FEMALE", "Nữ");

    String code;
    String name;
}
