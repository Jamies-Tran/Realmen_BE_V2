package com.capstone.realmen.controller.api.admin.booking;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.controller.api.admin.booking.models.BookingRequest;
import com.capstone.realmen.controller.api.admin.booking.models.IBookingModelMapper;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.booking.Booking;
import com.capstone.realmen.service.booking.BookingUseCaseService;
import com.capstone.realmen.service.booking.data.BookingCreateRequire;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController("AdminBookingController")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingController implements IBookingAPI {
    @NonNull
    BookingUseCaseService useCaseService;

    @NonNull
    IBookingModelMapper modelMapper;

    @Override
    public void save(BookingRequest request) {
        Booking booking = modelMapper.toDto(request);
        Account customer = Account.toCustomer(request.customer());

        useCaseService.adminCreateBookingByReceptionist(BookingCreateRequire.of(booking, customer));
    }
}
