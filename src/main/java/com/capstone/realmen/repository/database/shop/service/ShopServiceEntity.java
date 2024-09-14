package com.capstone.realmen.repository.database.shop.service;

import java.util.Objects;

import com.capstone.realmen.repository.database.audit.Auditable;
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
@Table(name = "shop_service")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShopServiceEntity extends Auditable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long shopServiceId;

        @Column(name = "shop_category_id")
        Long shopCategoryId;

        @Column(name = "shop_service_name")
        String shopServiceName;

        @Column(name = "shop_service_price")
        Long shopServicePrice;

        @Column(name = "shop_service_thumbnail")
        String shopServiceThumbnail;

        @Column(name = "estimate_duration")
        Integer estimateDuration;

        @Column(name = "duration_unit_code")
        String durationUnitCode;

        @Column(name = "duration_unit_name")
        String durationUnitName;

        public ShopServiceEntity setAudit(Auditable auditable) {
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
