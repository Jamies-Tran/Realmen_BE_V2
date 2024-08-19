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
        return Arrays.stream(EBranchStatus.values())
                .filter(bStatus -> (Objects.isNull(statuses) || statuses.isEmpty())
                        || statuses.contains(bStatus.getCode()))
                .map(BranchStatus::of)
                .toList();
    }

    public static EBranchStatus findByCode(String code) {
        return Arrays.stream(values())
                .filter(status -> Objects.equals(status.getCode(), code))
                .findAny()
                .orElse(null);
    }
}
