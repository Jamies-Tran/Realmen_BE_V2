package com.capstone.realmen.repository.database.plans.daily.service;

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
@Table(name = "daily_plan_service")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DailyPlanServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long dailyPlanServiceId;

    @Column(name = "daily_plan_id")
    Long dailyPlanId;

    @Column(name = "shop_service_id")
    Long shopServiceId;
}
