package com.capstone.realmen.common.enums;

import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;

import com.capstone.realmen.controller.handler.exceptions.NotFoundException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EPickUpType {
    PICKED("PICKED", "Đã chọn nhân viên"),
    NO_SPECIFIC("NO_SPECIFIC", "Chưa chọn nhân viên");

    String code;
    String name;

    public static List<EPickUpType> findAll(String search) {
        return Arrays.stream(values())
            .filter(pickUp -> {
                boolean isGetAll = !StringUtils.hasText(search);
                boolean filterByName = pickUp.getName().toLowerCase()
                    .contains(search.toLowerCase());
                return isGetAll || filterByName;
            }).toList();
    }

    public static EPickUpType findByCode(String code) {
        return Arrays.stream(values())
            .filter(pickUp -> pickUp.getCode().equals(code))
            .findAny()
            .orElseThrow(NotFoundException::new);
    }
}
