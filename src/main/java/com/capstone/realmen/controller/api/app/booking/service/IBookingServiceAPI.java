package com.capstone.realmen.controller.api.app.booking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.realmen.common.response.PageImplResponse;
import com.capstone.realmen.controller.api.app.booking.service.models.BookingServiceResponse;

@RequestMapping("/mobile/booking-service")
public interface IBookingServiceAPI {
    @GetMapping
    PageImplResponse<BookingServiceResponse> findAll(
        @RequestParam(required = false, value = "timeRange", defaultValue = "") 
        @DateTimeFormat(iso = ISO.DATE_TIME) List<LocalDateTime> timeRange,
        @RequestParam(required = false, value = "statusCodes", defaultValue = "") List<String> statusCodes,
        @RequestParam(required = false, value = "bookingId", defaultValue = "") Long bookingId,
        @RequestParam(required = false, value = "staffId", defaultValue = "") Long staffId,
        @RequestParam(required = false, value = "current", defaultValue = "1") Integer current,
        @RequestParam(required = false, value = "pageSize", defaultValue = "20") Integer pageSize
    );
}
