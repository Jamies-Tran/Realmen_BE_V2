package com.capstone.realmen.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EWorkingStatus {
    WORKING("WORKING", "Đang công tác"),
    PENDING("PENDING", "chờ công tác"),
    MOVE_OUT("MOVE_OUT", "Chuyển công tác"),
    QUIT("QUIT", "Đã nghỉ việc");

    String code;
    String name;
}
