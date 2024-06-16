package com.capstone.realmen.controller.api.app.authentication.models;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.With;

@With
@Builder
public record AppAccessTokenForStaffRequest(
        @NotNull(
            message = "Vui lòng nhập mã nhân viên"
        ) 
        String staffCode,
        @NotNull(
            message = "Vui lòng nhập mật khẩu"
        ) 
        String password) {

}
