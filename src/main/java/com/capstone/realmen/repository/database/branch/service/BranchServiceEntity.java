package com.capstone.realmen.repository.database.branch.service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;
import lombok.experimental.FieldDefaults;

@With
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "branch_service")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BranchServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long branchServiceId;

    @Column(name = "branch_id")
    Long branchId;

    @Column(name = "shop_service_id")
    Long shopServiceId;

    @Column(name = "branch_service_price")
    Long branchServicePrice;

    @Column(name = "branch_service_status_code")
    String branchServiceStatusCode;

    @Column(name = "branch_service_status_name")
    String branchServiceStatusName;
}
