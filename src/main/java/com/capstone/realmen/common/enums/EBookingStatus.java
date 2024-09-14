package com.capstone.realmen.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EBookingStatus {
    DRAFT("DRAFT", "Lưu nháp"),
    AWAITING_PROCESS("AWAITING_PROCESS", "Chờ thực hiện");

    String code;
    String name;
}
