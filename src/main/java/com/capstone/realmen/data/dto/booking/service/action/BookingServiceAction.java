package com.capstone.realmen.data.dto.booking.service.action;

import lombok.Builder;

@Builder
public record BookingServiceAction(
    Boolean allowAccept,
    Boolean allowBegin,
    Boolean allowEnd,
    Boolean allowDelete
) {
    public BookingServiceAction {
        allowAccept = false;
        allowBegin = false;
        allowEnd = false;
        allowDelete = false;
    }
}
