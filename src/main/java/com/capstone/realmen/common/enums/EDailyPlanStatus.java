package com.capstone.realmen.common.enums;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
public enum EDailyPlanStatus {
    OVERDUE("OVERDUE", "Quá hạn"),
    DRAFT("DRAFT", "Bản nháp"),
    PROCESSING("PROCESSING", "Đang hoạt động"),
    DISABLED("DISABLED", "Vô hiệu hóa");

    String code;
    String name;

    public static EDailyPlanStatus findByCode(String code) {
        return Arrays.stream(values()).filter(status -> Objects.equals(status.getCode(), code)).findAny().orElseThrow(NotFoundException::new);
    }

    public static EDailyPlanStatus verify(LocalDate dateTime, EDailyPlanStatus status) {
        return LocalDateTime.now().toLocalDate().compareTo(dateTime) <= 0 ? status
                : EDailyPlanStatus.OVERDUE;
    }
}
