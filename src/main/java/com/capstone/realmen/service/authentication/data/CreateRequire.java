package com.capstone.realmen.service.authentication.data;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record CreateRequire(
                String staffCode,
                String phone,
                String password) {

        public static CreateRequire of(String staffCode, String phone, String password) {
                return init().withStaffCode(staffCode).withPassword(password).withPhone(phone);
        }

        private static CreateRequire init() {
                return CreateRequire.builder().build();
        }
}
