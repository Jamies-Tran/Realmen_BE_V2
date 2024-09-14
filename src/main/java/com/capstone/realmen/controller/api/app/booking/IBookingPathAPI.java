package com.capstone.realmen.controller.api.app.booking;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/app/booking/{bookingId}")
public interface IBookingPathAPI {
    @DeleteMapping
    void delete(Long bookingId);
}
