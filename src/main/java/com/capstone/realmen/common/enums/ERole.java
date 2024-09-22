package com.capstone.realmen.common.enums;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ERole {
    SHOP_OWNER("SHOPOWNER", "Chủ sở hữu"),
    OPERATOR_STAFF("OPERATOR_STAFF", "Nhân viên vận hành"),
    BRANCH_MANAGER("BRANCHMANAGER", "Quản lý chi nhánh"),
    RECEPTIONIST("RECEPTIONIST", "Tiếp tân"),
    CUSTOMER("CUSTOMER", "Khách hàng");

    String code;
    String name;

    public static Optional<ERole> findByCode(String code) {
        return Arrays.stream(ERole.values())
                .filter(role -> Objects.equals(role.getCode(), code))
                .findAny();
    }

    public SimpleGrantedAuthority authority() {
        return new SimpleGrantedAuthority(String.format("ROLE_%s", this.getCode()));
    }
}
