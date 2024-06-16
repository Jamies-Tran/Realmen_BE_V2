package com.capstone.realmen.service.authentication.data;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record CreateRequire(
                String staffCode,
                String phone,
                String password) {

        public static CreateRequire byPhone(String phone, String password) {
                return init().withPassword(password).withPhone(phone);
        }

        public static CreateRequire byStaffCode(String staffCode, String password) {
                return init().withStaffCode(staffCode).withPassword(password);
        }

        private static CreateRequire init() {
                return CreateRequire.builder().build();
        }
}
