package com.capstone.realmen.common.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.capstone.realmen.data.dto.branch.status.BranchStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EBranchStatus {
    ACTIVE("ACTIVE", "Hoạt động"),
    INACTIVE("INACTIVE", "Chưa hoạt động"),
    DELETED("DELETED", "Ngưng hoạt động");

    String code;
    String name;

    public static List<BranchStatus> defaultStatuses(List<String> statuses) {
        List<BranchStatus> defaultStatuses = Arrays.stream(EBranchStatus.values())
                .filter(status -> !status.getCode().startsWith(EBranchStatus.DELETED.getCode()))
                .map(status -> BranchStatus.builder().code(status.getCode()).name(status.getName()).build())
                .toList();
        if (Objects.isNull(statuses) || statuses.isEmpty()) {
            return defaultStatuses;
        }
        return statuses.stream()
                .filter(status -> defaultStatuses.stream().map(BranchStatus::code).toList().contains(status))
                .map(status -> BranchStatus.builder()
                        .code(status)
                        .name(defaultStatuses.stream()
                                .filter(defaultStatus -> Objects.equals(status, defaultStatus.code()))
                                .findAny()
                                .get()
                                .name())
                        .build())
                .toList();
    }
}
