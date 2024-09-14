package com.capstone.realmen.controller.api.app.booking;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.controller.api.app.booking.models.BookingRequest;
import com.capstone.realmen.controller.api.app.booking.models.IBookingModelMapper;
import com.capstone.realmen.data.dto.booking.Booking;
import com.capstone.realmen.service.booking.BookingUseCaseService;
import com.capstone.realmen.service.booking.data.BookingCreateRequire;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingController implements IBookingAPI {
    @NonNull
    BookingUseCaseService useCaseService;

    @NonNull
    IBookingModelMapper modelMapper;

    @Override
    public void createByCustomer(BookingRequest bookingRequest) {
        Booking booking = modelMapper.toDto(bookingRequest);
        BookingCreateRequire createRequire = BookingCreateRequire.of(booking);

        useCaseService.appCreateByCusomter(createRequire);
    }
}
