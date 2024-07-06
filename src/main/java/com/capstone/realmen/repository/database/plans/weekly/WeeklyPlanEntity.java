package com.capstone.realmen.repository.database.plans.weekly;

import java.time.LocalDateTime;
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
@Table(name = "weekly_plan")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WeeklyPlanEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long weeklyPlanId;

    @Column(name = "branch_id")
    Long branchId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "begin_at")
    LocalDateTime beginAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "complete_at")
    LocalDateTime completeAt;

    @Column(name = "weekly_plan_status_code")
    String weeklyPlanStatusCode;

    @Column(name = "weekly_plan_status_name")
    String weeklyPlanStatusName;

    public WeeklyPlanEntity setAudit(Auditable auditable) {
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
