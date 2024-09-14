package com.capstone.realmen.controller.api.app.booking;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.service.booking.BookingUseCaseService;
import com.capstone.realmen.service.booking.data.BookingDeleteRequire;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingPathController implements IBookingPathAPI {
    @NonFinal
    BookingUseCaseService useCase;

    @Override
    public void delete(Long bookingId) {
        BookingDeleteRequire deleteRequire = BookingDeleteRequire.of(bookingId);

        useCase.appDeleteByCustomer(deleteRequire);
    }
}
