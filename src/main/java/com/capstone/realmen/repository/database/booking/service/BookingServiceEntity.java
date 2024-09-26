package com.capstone.realmen.repository.database.booking.service;

import java.time.LocalTime;
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
@Table(name = "booking_service")
public class BookingServiceEntity extends Auditable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long bookingServiceId;

        @Column(name = "booking_id")
        Long bookingId;

        @Column(name = "daily_plan_service_id")
        Long dailyPlanServiceId;

        @Column(name = "staff_id")
        Long staffId;

        @Column(name = "pick_up_type_code")
        String pickUpTypeCode;

        @Column(name = "pick_up_type_name")
        String pickUpTypeName;

        @Column(name = "status_code")
        String statusCode;

        @Column(name = "status_name")
        String statusName;

        @Column(name = "price")
        Long price;

        @Column(name = "begin_at")
        @Temporal(TemporalType.TIME)
        LocalTime beginAt;

        @Column(name = "finish_at")
        @Temporal(TemporalType.TIME)
        LocalTime finishAt;

        @Column(name = "actual_begin_at")
        @Temporal(TemporalType.TIME)
        LocalTime actualBeginAt;

        @Column(name = "actual_finished_at")
        @Temporal(TemporalType.TIME)
        LocalTime actualFinishedAt;

        public BookingServiceEntity setAudit(Auditable auditable) {
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
