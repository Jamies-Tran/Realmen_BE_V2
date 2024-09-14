package com.capstone.realmen.service.booking.other;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.realmen.data.dto.plans.daily.DailyPlanShiftHour;
import com.capstone.realmen.repository.database.booking.service.IBookingServiceRepository;
import com.capstone.realmen.service.booking.other.data.BookingServiceCountRequire;

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

    public List<DailyPlanShiftHour> countBookingInShift(BookingServiceCountRequire countRequire) {
        return countRequire.shifts().stream()
            .map(shift -> {
                Integer bookingCount = repository.coungBooking(countRequire, shift.from(), shift.to());
                return shift.withBookingCount(bookingCount);
                
            }).toList();
    }
}
