package com.capstone.realmen.service.booking.usecase.admin;

import com.capstone.realmen.service.booking.data.BookingCreateRequire;

public interface IAdminBookingService {
    void adminCreateBookingByReceptionist(BookingCreateRequire createRequire);
}
