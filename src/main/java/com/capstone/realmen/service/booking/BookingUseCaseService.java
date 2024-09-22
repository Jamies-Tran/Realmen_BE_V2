package com.capstone.realmen.service.booking;

import org.springframework.stereotype.Service;

import com.capstone.realmen.data.dto.booking.Booking;
import com.capstone.realmen.service.booking.data.BookingCreateRequire;
import com.capstone.realmen.service.booking.data.BookingDeleteRequire;
import com.capstone.realmen.service.booking.usecase.admin.IAdminBookingService;
import com.capstone.realmen.service.booking.usecase.app.IAppBookingService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingUseCaseService implements IAppBookingService, IAdminBookingService {
    @NonNull
    BookingCommandService command;

    @Override
    public void appCreateByCusomter(BookingCreateRequire createRequire) {
        command.saveByCus(createRequire);
    }

    @Override
    public void appDeleteByCustomer(BookingDeleteRequire deleteRequire) {
        command.delete(deleteRequire);
    }

    @Override
    public void adminCreateBookingByReceptionist(BookingCreateRequire createRequire) {
        command.saveByRecept(createRequire);
    }
}
