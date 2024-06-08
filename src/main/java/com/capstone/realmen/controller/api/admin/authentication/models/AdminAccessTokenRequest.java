package com.capstone.realmen.controller.api.admin.authentication.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.With;

@With
@Builder
public record AdminAccessTokenRequest( 
        @NotNull(
            message = "Vui lòng nhập mã nhân viên"
        ) 
        String staffCode,
        @Size(
            message = "mật khẩu không được bỏ trống"
        ) 
        @NotNull(
            message = "Vui lòng nhập mật khẩu"
        ) 
        String password) {

    public static AdminAccessTokenRequest of(String staffCode, String password) {
        return init().withStaffCode(staffCode).withPassword(password);
    }

    private static AdminAccessTokenRequest init() {
        return AdminAccessTokenRequest.builder().build();
    }
}
