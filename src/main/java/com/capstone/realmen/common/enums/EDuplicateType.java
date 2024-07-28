package com.capstone.realmen.common.enums;

import java.util.Arrays;
import java.util.Objects;

import com.capstone.realmen.controller.handler.exceptions.NotFoundException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EDuplicateType {
    TO_PRESENT("TO_PRESENT"),
    TO_NEXT_WEEK("TO_NEXT_WEEK");

    String code;

    public static EDuplicateType findByCode(String code) {
        return Arrays.stream(values())
                .filter(type -> Objects.equals(type.getCode(), code))
                .findAny()
                .orElseThrow(NotFoundException::new);
    }
}
