package com.capstone.realmen.controller.api.admin.account.models.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ERoleRequest {
    OPERATOR_STAFF("OPERATOR_STAFF", "Nhân viên vận hành"),
    BRANCH_MANAGER("BRANCHMANAGER", "Quản lý chi nhánh"),
    RECPETIONIST("RECPETIONIST", "Tiếp tân");

    String code;
    String name;
}
