package com.capstone.realmen.service.booking.other;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.booking.service.BookingService;
import com.capstone.realmen.data.dto.booking.service.IBookingServiceMapper;
import com.capstone.realmen.data.dto.plans.daily.DailyPlanShiftHour;
import com.capstone.realmen.repository.database.booking.service.IBookingServiceRepository;
import com.capstone.realmen.service.booking.other.data.BookingServiceCountRequire;
import com.capstone.realmen.service.booking.other.data.BookingServiceSearchCriteria;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingServiceQueryService {
    @NonNull
    IBookingServiceRepository repository;

    @NonNull
    IBookingServiceMapper mapper;

    public List<DailyPlanShiftHour> countBookingInShift(BookingServiceCountRequire countRequire) {
        return countRequire.shifts().stream()
            .map(shift -> {
                Integer bookingCount = repository.coungBooking(countRequire, shift.from(), shift.to());
                return shift.withBookingCount(bookingCount);
            }).toList();
    }

    public Page<BookingService> findAll(BookingServiceSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return repository.findAll(searchCriteria, pageRequestCustom.pageRequest())
            .map(mapper::toDto);
    }
}
