package com.capstone.realmen.service.booking.other;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.booking.service.BookingService;
import com.capstone.realmen.service.booking.other.data.BookingServiceDeleteRequire;
import com.capstone.realmen.service.booking.other.data.BookingServiceSearchCriteria;
import com.capstone.realmen.service.booking.other.usecase.app.IAppBookingServiceService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingServiceUseCaseService implements IAppBookingServiceService {
    @NonNull
    BookingServiceCommandService command;

    @NonNull
    BookingServiceQueryService query;

    @Override
    public void appDelete(BookingServiceDeleteRequire deleteRequire) {
        command.delete(deleteRequire);
    }

    @Override
    public Page<BookingService> findAll(BookingServiceSearchCriteria searchCriteria,
            PageRequestCustom pageRequestCustom) {
        return query.findAll(searchCriteria, pageRequestCustom);
    }
}
