package com.capstone.realmen.repository.database.account.plans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;
import lombok.experimental.FieldDefaults;

@With
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "daily_plan_account")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DailyPlanAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_plan_accoun_id")
    Long dailyPlanAccountId;

    @Column(name = "daily_plan_id")
    Long dailyPlanId;

    @Column(name = "account_id")
    Long accountId;

    @Column(name = "shift_code")
    String shiftCode;

    @Column(name = "shift_name")
    String shiftName;
}
