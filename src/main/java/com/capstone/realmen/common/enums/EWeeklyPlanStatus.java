package com.capstone.realmen.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EWeeklyPlanStatus {
    DRAFT("DRAFT", "Bản nháp"),
    ENABLED("ENABLED", "Kích hoạt"),
    DISABLED("DISABLED", "Vô hiệu hóa");

    String code;
    String name;
}
