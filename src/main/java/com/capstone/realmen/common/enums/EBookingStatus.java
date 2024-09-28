package com.capstone.realmen.common.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.capstone.realmen.controller.handler.exceptions.NotFoundException;

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

    public static EBookingStatus findByCode(String code) {
        return Arrays.stream(values()).filter(status -> Objects.equals(code, status.getCode()))
            .findAny()
            .orElseThrow(NotFoundException::new);
    }
}
