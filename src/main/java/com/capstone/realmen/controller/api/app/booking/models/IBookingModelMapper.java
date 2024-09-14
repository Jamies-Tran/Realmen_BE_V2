package com.capstone.realmen.controller.api.app.booking.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dto.booking.Booking;

@Mapper(componentModel = "spring", implementationName = "AppBooking", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBookingModelMapper {
    Booking toDto(BookingRequest model);
}
