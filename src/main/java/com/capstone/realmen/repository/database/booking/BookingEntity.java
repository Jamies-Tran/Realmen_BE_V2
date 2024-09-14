package com.capstone.realmen.repository.database.booking;

import java.time.LocalDate;
import java.util.Objects;

import com.capstone.realmen.repository.database.audit.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@With
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class BookingEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long bookingId;

    @Column(name = "account_id")
    Long accountId;

    @Column(name = "branch_id")
    Long branchId;

    @Column(name = "daily_plan_id")
    Long dailyPlanId;

    @Column(name = "booked_at")
    LocalDate bookedAt;

    @Column(name = "booking_code")
    String bookingCode;

    @Column(name = "booking_method_code")
    String bookingMethodCode;

    @Column(name = "booking_method_name")
    String bookingMethodName;

    @Column(name = "status_code")
    String statusCode;

    @Column(name = "status_name")
    String statusName;

    public BookingEntity setAudit(Auditable auditable) {
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
