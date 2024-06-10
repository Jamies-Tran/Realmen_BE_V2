package com.capstone.realmen.data.dto.account;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AccountCreated(
        Long accountId,
        Boolean isActivated) {
    public static AccountCreated byShopOwner(Long accountId) {
        return init().withAccountId(accountId).withIsActivated(false);
    }

    public static AccountCreated byManager(Long accountId) {
        return init().withAccountId(accountId).withIsActivated(true);
    }

    public static AccountCreated byReceptionist(Long accountId) {
        return init().withAccountId(accountId).withIsActivated(false);
    }

    public static AccountCreated byDefault(Long accountId) {
        return init().withAccountId(accountId).withIsActivated(true);
    }

    private static AccountCreated init() {
        return AccountCreated.builder().build();
    }
}
