package com.capstone.realmen.service.booking;

import org.springframework.stereotype.Service;

import com.capstone.realmen.service.booking.data.BookingCreateRequire;
import com.capstone.realmen.service.booking.data.BookingDeleteRequire;
import com.capstone.realmen.service.booking.usecase.app.IAppBookingService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingUseCaseService implements IAppBookingService {
    @NonNull
    BookingCommandService command;

    @Override
    public void appCreateByCusomter(BookingCreateRequire createRequire) {
        command.saveOrUpdate(createRequire);
    }

    @Override
    public void appDeleteByCustomer(BookingDeleteRequire deleteRequire) {
        command.delete(deleteRequire);
    }
}
