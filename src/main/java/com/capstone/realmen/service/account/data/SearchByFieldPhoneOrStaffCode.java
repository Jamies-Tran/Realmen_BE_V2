package com.capstone.realmen.service.account.data;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record SearchByFieldPhoneOrStaffCode(String search) {

    public static SearchByFieldPhoneOrStaffCode of(String search) {
        return init().withSearch(search);
    }

    private static SearchByFieldPhoneOrStaffCode init() {
        return SearchByFieldPhoneOrStaffCode.builder().build();
    }
}
