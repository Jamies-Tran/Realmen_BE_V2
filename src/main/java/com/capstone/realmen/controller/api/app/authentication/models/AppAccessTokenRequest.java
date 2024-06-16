package com.capstone.realmen.controller.api.app.authentication.models;

import com.capstone.realmen.common.validate.Phone;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.With;

@With
@Builder
public record AppAccessTokenRequest(
    @Phone 
    String phone,
    @NotNull 
    @Size(
        min = 5,
        max = 5,
        message = "Mã otp không hợp lệ"
    ) 
    String otp
) {
    
}
