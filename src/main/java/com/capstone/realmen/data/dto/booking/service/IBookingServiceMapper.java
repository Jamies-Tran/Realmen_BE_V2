package com.capstone.realmen.data.dto.booking.service;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dao.booking.service.BookingServiceDAO;
import com.capstone.realmen.repository.database.booking.service.BookingServiceEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBookingServiceMapper {
    BookingServiceEntity toEntity(BookingService dto);

    BookingService toDto(BookingServiceEntity entity);

    BookingService toDto(BookingServiceDAO dao);
}
