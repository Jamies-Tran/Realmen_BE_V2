package com.capstone.realmen.repository.database.plans.daily;

import java.time.LocalDateTime;

import com.capstone.realmen.repository.database.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Table(name = "daily_plan")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DailyPlanEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long dailyPlanId;

    @Column(name = "weekly_plan_id")
    Long weeklyPlanId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    LocalDateTime date;
}
