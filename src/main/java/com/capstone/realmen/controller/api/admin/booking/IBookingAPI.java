package com.capstone.realmen.controller.api.admin.booking;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.realmen.controller.api.admin.booking.models.BookingRequest;

@RequestMapping("/web/booking")
public interface IBookingAPI {
    @PostMapping
    void save(@RequestBody BookingRequest request);
    
}
