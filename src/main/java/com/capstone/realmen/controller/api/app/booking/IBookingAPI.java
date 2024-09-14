package com.capstone.realmen.controller.api.app.booking;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.realmen.controller.api.app.booking.models.BookingRequest;

@RequestMapping("/mobile/booking")
public interface IBookingAPI {
    @PostMapping
    @PreAuthorize("hasAnyRole({'ROLE_CUSTOMER', 'ROLE_RECPETIONIST'})")
    void createByCustomer(@RequestBody BookingRequest bookingRequest);

}
