package com.capstone.realmen.service.booking.other.usecase.app;

import org.springframework.data.domain.Page;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.booking.service.BookingService;
import com.capstone.realmen.service.booking.other.data.BookingServiceDeleteRequire;
import com.capstone.realmen.service.booking.other.data.BookingServiceSearchCriteria;

public interface IAppBookingServiceService {
    void appDelete(BookingServiceDeleteRequire deleteRequire);

    Page<BookingService> findAll(BookingServiceSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom);
}
