package com.capstone.realmen.service.booking.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import com.capstone.realmen.common.enums.EPickUpType;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.booking.Booking;
import com.capstone.realmen.data.dto.booking.service.BookingService;

import lombok.Builder;

@Builder
public record BookingCreateRequire(Booking booking, Account customer) {
        public static BookingCreateRequire of(Booking booking) {
                return BookingCreateRequire.builder()
                                .booking(booking.withBookingCode(getBookingCode()))
                                .build();
        }

        public static BookingCreateRequire of(Booking booking, Account customer) {
                return BookingCreateRequire.builder()
                                .booking(booking.withBookingCode(getBookingCode()))
                                .customer(customer)
                                .build();
        }

        public String customerPhone() {
                return customer.phone();
        }

        public List<BookingService> bookingServices() {

                return booking.bookingServices()
                                .stream()
                                .map(bService -> {
                                        String pickUpTypeCode = Objects.isNull(bService.staffId())
                                                        ? EPickUpType.NO_SPECIFIC.getCode()
                                                        : EPickUpType.PICKED.getCode();
                                        String pickUpTypeName = Objects.isNull(bService.staffId())
                                                        ? EPickUpType.NO_SPECIFIC.getName()
                                                        : EPickUpType.PICKED.getName();
                                        return bService
                                                        .withPickUpTypeCode(pickUpTypeCode)
                                                        .withPickUpTypeName(pickUpTypeName);
                                })
                                .toList();
        }

        public Boolean isAccountIdEmpty() {
                return Objects.isNull(customer.accountId());
        }

        public List<Long> dailyPlanServiceIds() {
                return booking.bookingServices()
                                .stream()
                                .map(BookingService::dailyPlanServiceId)
                                .toList();
        }

        private static String getBookingCode() {
                return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        }
}
