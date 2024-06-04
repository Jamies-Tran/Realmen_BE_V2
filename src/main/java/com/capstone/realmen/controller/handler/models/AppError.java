package com.capstone.realmen.controller.handler.models;

import java.util.Objects;

import com.capstone.realmen.common.enums.EAppError;
import com.capstone.realmen.controller.handler.exceptions.AccessTokenException;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AppError(
        String code,
        String message) {

    public static AppError accsessTokenException(AccessTokenException exc) {
        String message = message(exc, EAppError.TOKEN_EXCEPTION.getMessage());
        return AppError.builder()
                .code(EAppError.TOKEN_EXCEPTION.getCode())
                .message(message)
                .build();
    }

    public static AppError notFoundException(NotFoundException exc) {
        String message = message(exc, EAppError.NOT_FOUND.getMessage());
        return AppError.builder()
                .code("")
                .message(message)
                .build();
    }

    private static String message(RuntimeException exc, String alternativeMessage) {
        return Objects.requireNonNullElse(exc.getMessage(), EAppError.NOT_FOUND.getMessage());
    }
}
