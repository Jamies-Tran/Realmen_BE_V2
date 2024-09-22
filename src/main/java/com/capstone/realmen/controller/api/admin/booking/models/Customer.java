package com.capstone.realmen.controller.api.admin.booking.models;

import lombok.Builder;

@Builder
public record Customer(
        Long accountId,
        String firstName,
        String lastName,
        String phone) {

}
