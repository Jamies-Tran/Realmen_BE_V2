package com.capstone.realmen.repository.database.plans.daily;

import java.time.LocalDate;
import java.util.Objects;
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

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    LocalDate date;

    @Column(name = "daily_plan_status_code")
    String dailyPlanStatusCode;

    @Column(name = "day_in_week_code")
    String dayInWeekCode;

    @Column(name = "day_in_week_name")
    String dayInWeekName;

    @Column(name = "daily_plan_status_name")
    String dailyPlanStatusName;

    public DailyPlanEntity setAudit(Auditable auditable) {
        this.setCreatedBy(Objects.nonNull(auditable.getCreatedBy())
                ? auditable.getCreatedBy()
                : this.getCreatedBy());
        this.setCreatedAt(Objects.nonNull(auditable.getCreatedAt())
                ? auditable.getCreatedAt()
                : this.getCreatedAt());
        this.setUpdatedBy(Objects.nonNull(auditable.getUpdatedBy())
                ? auditable.getUpdatedBy()
                : this.getUpdatedBy());
        this.setUpdatedAt(Objects.nonNull(auditable.getUpdatedAt())
                ? auditable.getUpdatedAt()
                : this.getUpdatedAt());
        return this;
    }
}
