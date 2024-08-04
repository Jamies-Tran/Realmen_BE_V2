package com.capstone.realmen.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EShift {
    MORNING_SHIFT("MORNING_SHIFT", "Ca sáng"),
    NIGHT_SHIFT("NIGHT_SHIFT", "Ca tối");

    String code;
    String name;
}
