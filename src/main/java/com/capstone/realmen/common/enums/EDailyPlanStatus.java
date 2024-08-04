package com.capstone.realmen.common.enums;

import java.time.LocalDateTime;

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
    ENABLED("ENABLED", "Kích hoạt"),
    DISABLED("DISABLED", "Vô hiệu hóa");

    String code;
    String name;

    public static EDailyPlanStatus verify(LocalDateTime dateTime) {
        return LocalDateTime.now().toLocalDate().compareTo(dateTime.toLocalDate()) <= 0 ? EDailyPlanStatus.DRAFT
                : EDailyPlanStatus.OVERDUE;
    }
}
