package com.capstone.realmen.controller.handler.models;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record ErrorResponse(
        String errorCode,
        String errorMsg,
        Integer statusCode,
        String status,
        LocalDateTime issueAt) {

}
