package com.capstone.realmen.controller.handler.models;

import java.util.Objects;

import com.capstone.realmen.common.enums.EAppError;
import com.capstone.realmen.controller.handler.exceptions.AccessTokenException;
import com.capstone.realmen.controller.handler.exceptions.ConflicException;
import com.capstone.realmen.controller.handler.exceptions.InvalidRequest;
import com.capstone.realmen.controller.handler.exceptions.LoginException;
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
                .code(EAppError.NOT_FOUND.getCode())
                .message(message)
                .build();
    }

    public static AppError loginException(LoginException exc) {
        String message = message(exc, EAppError.AUTH_EXCEPTION.getMessage());
        return AppError.builder()
                .code(EAppError.AUTH_EXCEPTION.getMessage())
                .message(message)
                .build();
    }

    public static AppError invalidRequest(InvalidRequest exc) {
        String message = message(exc, EAppError.INVALID_REQUEST.getMessage());
        return AppError.builder()
                .code(EAppError.INVALID_REQUEST.getCode())
                .message(message)
                .build();
    }

    public static AppError conflicException(ConflicException exc) {
        String message = message(exc, EAppError.CONFLICT_EXCEPTION.getMessage());
        return AppError.builder()
                .code(EAppError.CONFLICT_EXCEPTION.getCode())
                .message(message)
                .build();
    }

    private static String message(RuntimeException exc, String alternativeMessage) {
        return Objects.requireNonNullElse(exc.getMessage(), alternativeMessage);
    }
}
