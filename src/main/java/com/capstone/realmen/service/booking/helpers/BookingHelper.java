package com.capstone.realmen.service.booking.helpers;

import com.capstone.realmen.common.enums.EBookingMethod;
import com.capstone.realmen.common.enums.EBookingStatus;
import com.capstone.realmen.common.enums.ERole;
import com.capstone.realmen.repository.database.booking.BookingEntity;

public class BookingHelper {
    protected BookingEntity createBookingByRole(BookingEntity booking, String accountRole, Long accountId) {
        switch (ERole.findByCode(accountRole).get()) {
            case CUSTOMER:
                return booking
                        .withAccountId(accountId)
                        .withBookingMethodCode(EBookingMethod.APP.getCode())
                        .withBookingMethodCode(EBookingMethod.APP.getCode())
                        .withStatusCode(EBookingStatus.DRAFT.getCode())
                        .withStatusName(EBookingStatus.DRAFT.getName());
            case RECPETIONIST:
                return booking
                        .withAccountId(accountId)
                        .withBookingMethodCode(EBookingMethod.DIRECT.getCode())
                        .withBookingMethodCode(EBookingMethod.DIRECT.getCode())
                        .withStatusCode(EBookingStatus.AWAITING_PROCESS.getCode())
                        .withStatusName(EBookingStatus.AWAITING_PROCESS.getName());
            default:
                return null;
        }
    }

    
}
