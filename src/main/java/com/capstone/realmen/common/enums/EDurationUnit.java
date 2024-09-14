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
public enum EDurationUnit {
    MINUTE("MINUTE", "Phút"),
    HOUR("HOUR", "Giờ");

    String code;
    String name;

    public static List<EDurationUnit> findAll(String search) {
        return Arrays.stream(values())
                .filter(unit -> {
                    boolean isGetAll = !StringUtils.hasText(search);
                    boolean filterByName = unit.getName().toLowerCase().contains(search.toLowerCase());
                    return isGetAll || filterByName;
                }).toList();
    }

    public static EDurationUnit findByCode(String code) {
        return Arrays.stream(values())
                .filter(unit -> unit.getCode().equals(code))
                .findAny()
                .orElseThrow(NotFoundException::new);
    }
}
