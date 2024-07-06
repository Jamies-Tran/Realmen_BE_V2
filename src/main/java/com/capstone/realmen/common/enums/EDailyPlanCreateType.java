package com.capstone.realmen.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EDailyPlanCreateType {
    WEEKLY("WEEKLY"),
    MONTHLY("MONTHLY");

    String code;

}
