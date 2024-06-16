package com.capstone.realmen.repository.database.audit;

import java.time.LocalDateTime;

import com.capstone.realmen.data.dto.account.Account;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import lombok.experimental.FieldDefaults;

@With
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Auditable {
    @Column(name = "created_by")
    String createdBy;
    @Column(name = "updated_by")
    String updatedBy;
    @Column(name = "created_at")
    LocalDateTime createdAt;
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    public static Auditable ofDefault() {
        return init()
                .withCreatedBy("-")
                .withCreatedAt(LocalDateTime.now());
    }

    public static Auditable ofCreated(Account account) {
        return init()
                .withCreatedBy(accountFullName(account))
                .withCreatedAt(LocalDateTime.now());

    }

    public static Auditable ofUpdated(Account account) {
        return init()
                .withUpdatedBy(accountFullName(account))
                .withUpdatedAt(LocalDateTime.now());

    }

    private static Auditable init() {
        return new Auditable();
    }

    private static String accountFullName(Account account) {
        return account.firstName().concat(" ").concat(account.lastName());
    }
}
