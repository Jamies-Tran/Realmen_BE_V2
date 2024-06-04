package com.capstone.realmen.common.enums;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;



@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EAppError {
    TOKEN_EXCEPTION("RM001", "Yêu cầu đăng nhập không hợp lệ"),
    NOT_FOUND("RM002", "Không tìm thấy dữ liệu");

    String code;
    String message;
}
