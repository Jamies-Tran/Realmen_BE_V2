package com.capstone.realmen.common.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EAccountStatus {
    ACTIVE("ACTIVE", "Kích hoạt"),
    PENDING_ACTIVE("PENDING_ACTIVE", "Chờ kích hoạt"),
    PENDING_BRANCH("PENDING_BRANCH", "Chờ kích hoạt"),
    DELETED("DELETED", "Đã xóa");

    String code;
    String name;

    public static List<String> defaultStatuses(List<String> statuses) {
        List<String> defaultStatuses = Arrays.stream(EAccountStatus.values())
                .filter(status -> !status.getCode().contains(EAccountStatus.DELETED.getCode()))
                .map(EAccountStatus::getCode)
                .toList();
        if (Objects.isNull(statuses) || statuses.isEmpty()) {
            return defaultStatuses;
        }
        return statuses.stream()
                .filter(status -> defaultStatuses.contains(status))
                .toList();
    }
}
