package com.capstone.realmen.data.dto.account;

import java.time.LocalDate;

import com.capstone.realmen.controller.api.admin.booking.models.Customer;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record Account(
        Long accountId,
        Long branchId,
        String firstName,
        String lastName,
        String phone,
        String address,
        String password,
        String staffCode,
        String roleCode,
        String roleName,
        String professionalTypeCode,
        String professionalTypeName,
        String thumbnail,
        LocalDate dob,
        String genderCode,
        String genderName,
        String accountStatusCode,
        String accountStatusName) {
                public static Account toCustomer(Customer customer) {
                        return Account.builder()
                                .accountId(customer.accountId())
                                .firstName(customer.firstName())
                                .lastName(customer.lastName())
                                .phone(customer.phone())
                                .build();
                }

}
