package com.capstone.realmen.service.booking;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.booking.Booking;
import com.capstone.realmen.data.dto.booking.IBookingMapper;
import com.capstone.realmen.repository.database.booking.IBookingRepository;
import com.capstone.realmen.service.booking.data.BookingSearchCriteria;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingQueryService {
    @NonNull
    IBookingRepository repository;

    @NonNull
    IBookingMapper mapper;

    public Page<Booking> findAll(BookingSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return repository.findAll(searchCriteria, pageRequestCustom.pageRequest())
            .map(mapper::toDto);
    }
}
