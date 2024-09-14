package com.capstone.realmen.service.booking.other;

import org.springframework.stereotype.Service;

import com.capstone.realmen.service.booking.other.data.BookingServiceDeleteRequire;
import com.capstone.realmen.service.booking.other.usecase.app.IBookingServiceService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingServiceUseCaseService implements IBookingServiceService {
    @NonNull
    BookingServiceCommandService command;

    @Override
    public void appDelete(BookingServiceDeleteRequire deleteRequire) {
        command.delete(deleteRequire);
    }
}
