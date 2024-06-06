package com.capstone.realmen.controller.api.admin.authentication.models;

import com.capstone.realmen.common.validate.Phone;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.With;

@With
@Builder
public record AccessTokenRequest(
        @Phone 
        @NotNull(
            message = "Vui lòng nhập số điện thoại"
        ) 
        String phone,
        @Size(
            min = 10, 
            max = 20, 
            message = "mật khẩu phải có khoản từ 10 đến 20 ký tự"
        ) 
        @NotNull(
            message = "Vui lòng nhập mật khẩu"
        ) 
        String password) {

    public static AccessTokenRequest of(String phone, String password) {
        return init().withPhone(phone).withPassword(password);
    }

    private static AccessTokenRequest init() {
        return AccessTokenRequest.builder().build();
    }
}
