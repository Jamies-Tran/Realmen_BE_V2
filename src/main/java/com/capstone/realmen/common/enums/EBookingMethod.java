package com.capstone.realmen.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EBookingMethod {
    APP("APP", "Realman App"),
    DIRECT("DIRECT", "Trực tiếp");

    String code;
    String name;
}
