package com.capstone.realmen.controller.api.app.booking.service;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.service.booking.other.BookingServiceUseCaseService;
import com.capstone.realmen.service.booking.other.data.BookingServiceDeleteRequire;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingServicePathController implements IBookingServicePathAPI {
    @NonNull
    BookingServiceUseCaseService useCase;

    @Override
    public void delete(Long bookingServiceId) {
        BookingServiceDeleteRequire deleteRequire = BookingServiceDeleteRequire.of(bookingServiceId);

        useCase.appDelete(deleteRequire);
    }
}
