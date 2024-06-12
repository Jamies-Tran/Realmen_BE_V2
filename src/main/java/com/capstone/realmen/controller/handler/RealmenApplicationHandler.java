package com.capstone.realmen.controller.handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capstone.realmen.controller.handler.exceptions.AccessTokenException;
import com.capstone.realmen.controller.handler.exceptions.InvalidRequest;
import com.capstone.realmen.controller.handler.exceptions.LoginException;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.controller.handler.models.AppError;
import com.capstone.realmen.controller.handler.models.ErrorResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class RealmenApplicationHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    Map<String, String> validationNotValidException(ConstraintViolationException exc) {
        Map<String, String> errors = new HashMap<>();
        exc.getConstraintViolations().forEach(error -> {
            String fieldError = "Validation fail";
            String errorMsg = error.getMessage();
            errors.put(fieldError, errorMsg);
        });
        return errors;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException exc) {

        return ErrorResponse.builder()
                .errorCode("")
                .errorMsg(exc.getAllErrors().stream()
                        .map(ObjectError::getDefaultMessage).findAny().orElse(
                                ""))
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .issueAt(LocalDateTime.now())
                .build();
    }

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

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(LoginException.class)
    public ErrorResponse loginExceptionHandler(LoginException exc) {
        AppError appError = AppError.loginException(exc);
        return ErrorResponse.builder()
                .errorCode(appError.code())
                .errorMsg(appError.message())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .issueAt(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRequest.class)
    public ErrorResponse invalidRequest(InvalidRequest exc) {
        AppError appError = AppError.invalidRequest(exc);
        return ErrorResponse.builder()
                .errorCode(appError.code())
                .errorMsg(appError.message())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .issueAt(LocalDateTime.now())
                .build();
    }
}
