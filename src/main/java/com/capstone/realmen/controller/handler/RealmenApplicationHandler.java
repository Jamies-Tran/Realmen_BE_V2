package com.capstone.realmen.controller.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capstone.realmen.controller.handler.exceptions.AccessTokenException;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.controller.handler.models.AppError;
import com.capstone.realmen.controller.handler.models.ErrorResponse;

@RestControllerAdvice
public class RealmenApplicationHandler {
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessTokenException.class)
    public ErrorResponse accessTokenExceptionHandler(AccessTokenException exc) {
        AppError appError = AppError.accsessTokenException(exc);
        return ErrorResponse.builder()
                .errorCode(appError.code())
                .errorMsg(appError.message())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .issueAt(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse notFoundExceptionHandler(NotFoundException exc) {
        AppError appError = AppError.notFoundException(exc);
        return ErrorResponse.builder()
                .errorCode(appError.code())
                .errorMsg(appError.message())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .issueAt(LocalDateTime.now())
                .build();
    }
}
