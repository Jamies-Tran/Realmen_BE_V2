package com.capstone.realmen.service.booking.usecase.app;

import com.capstone.realmen.service.booking.data.BookingCreateRequire;
import com.capstone.realmen.service.booking.data.BookingDeleteRequire;

public interface IAppBookingService {
    void appCreateByCusomter(BookingCreateRequire createRequire);

    void appDeleteByCustomer(BookingDeleteRequire deleteRequire);
}
