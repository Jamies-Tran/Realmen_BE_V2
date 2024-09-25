package com.capstone.realmen.data.dto.booking.service;

import java.time.LocalTime;
import java.util.Objects;

import com.capstone.realmen.common.enums.EBookingStatus;
import com.capstone.realmen.common.enums.ERole;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.booking.service.action.BookingServiceAction;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record BookingService(
                Long bookingServiceId,
                Long branchServiceId,
                Long bookingId,
                Long serviceId,
                Long dailyPlanServiceId,
                Long staffId,
                Long price,
                String pickUpTypeCode,
                String pickUpTypeName,
                LocalTime beginAt,
                LocalTime finishAt,
                LocalTime actualBeginAt,
                LocalTime actualFinishedAt,
                String statusCode,
                String statusName,
                BookingServiceAction action) {
        public BookingService setAction(Account account) {
                Long accountId = account.accountId();
                String accountRole = account.roleCode();
                BookingServiceAction.BookingServiceActionBuilder actionBuilder = BookingServiceAction.builder();
                switch (EBookingStatus.findByCode(statusCode)) {
                        case DRAFT:
                                actionBuilder
                                        .allowAccept(false)
                                        .allowBegin(false)
                                        .allowEnd(false)
                                        .allowDelete(false);
                                break;
                        case AWAITING_PROCESS:

                                if (Objects.equals(accountRole, ERole.OPERATOR_STAFF.getCode())) {
                                        if (Objects.equals(accountId, staffId)) {
                                                actionBuilder
                                                        .allowAccept(true)
                                                        .allowBegin(true)
                                                        .allowEnd(true)
                                                        .allowDelete(false);
                                        } else {
                                                actionBuilder
                                                        .allowAccept(false)
                                                        .allowBegin(false)
                                                        .allowEnd(false)
                                                        .allowDelete(false);
                                        }
                                } else if (Objects.equals(accountRole, ERole.CUSTOMER.getCode())) {
                                        actionBuilder
                                                .allowAccept(false)
                                                .allowBegin(false)
                                                .allowEnd(false)
                                                .allowDelete(true);
                                }
                }

                return this.withAction(actionBuilder.build());
        }

}
