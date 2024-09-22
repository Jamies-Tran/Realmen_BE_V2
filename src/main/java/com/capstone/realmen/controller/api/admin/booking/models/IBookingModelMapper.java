package com.capstone.realmen.controller.api.admin.booking.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dto.booking.Booking;

@Mapper(
    componentModel = "spring", 
    unmappedTargetPolicy = ReportingPolicy.IGNORE, 
    implementationName = "AdminBooking")
public interface IBookingModelMapper {
    Booking toDto(BookingRequest model);
}
