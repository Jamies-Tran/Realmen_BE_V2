package com.capstone.realmen.repository.database.branch;

import java.time.LocalTime;
import java.util.Objects;

import com.capstone.realmen.data.dto.branch.address.Address;
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
@Table(name = "branch")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BranchEntity extends Auditable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "branch_id")
        Long branchId;
        @Column(name = "branch_name")
        String branchName;
        @Column(name = "branch_manager_code")
        String branchManagerCode;
        @Column(name = "branch_hotline")
        String branchHotline;
        @Column(name = "branch_thumbnail")
        String branchThumbnail;
        @Column(name = "branch_street")
        String branchStreet;
        @Column(name = "branch_ward")
        String branchWard;
        @Column(name = "branch_district")
        String branchDistrict;
        @Column(name = "branch_province")
        String branchProvince;
        @Column(name = "latitude")
        Double latitude;
        @Column(name = "longitude")
        Double longitude;
        @Column(name = "open")
        @Temporal(TemporalType.TIME)
        LocalTime open;
        @Column(name = "close")
        @Temporal(TemporalType.TIME)
        LocalTime close;
        @Column(name = "branch_status_code")
        String branchStatusCode;
        @Column(name = "branch_status_name")
        String branchStatusName;

        public BranchEntity setAudit(Auditable auditable) {
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

        public BranchEntity withAddress(Address address) {
                return this
                        .withBranchStreet(address.branchStreet())
                        .withBranchDistrict(address.branchDistrict())
                        .withBranchWard(address.branchWard())
                        .withBranchProvince(address.branchProvince())
                        .withLatitude(address.latitude())
                        .withLongitude(address.longitude());
        }
}
