package com.capstone.realmen.data.dto.booking;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.repository.database.booking.BookingEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBookingMapper {
    Booking toDto(BookingEntity entity);

    BookingEntity toEntity(Booking dto);
}
