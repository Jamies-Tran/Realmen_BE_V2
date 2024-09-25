package com.capstone.realmen.controller.api.app.booking.service.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dto.booking.service.BookingService;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, implementationName = "AppBookingServiceModelMapper")
public interface IBookingServiceModelMapper {
    BookingServiceResponse toModel(BookingService dto);
}
