package com.capstone.realmen.controller.api.app.booking.service;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/app/booking-service/{bookingServiceId}")
public interface IBookingServicePathAPI {
    @DeleteMapping
    void delete(@PathVariable Long bookingServiceId);
}
