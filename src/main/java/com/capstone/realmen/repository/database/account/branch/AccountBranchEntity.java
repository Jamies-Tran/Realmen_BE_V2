package com.capstone.realmen.repository.database.account.branch;

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
@Table(name = "account_branch")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountBranchEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountBranchId;
    @Column(name = "account_id")
    Long accountId;
    @Column(name = "branch_id")
    Long branchId;
    @Column(name = "working_status_code")
    String workingStatusCode;
    @Column(name = "workingStatusName")
    String workingStatusName;
    @Column(name = "is_manager")
    Boolean isManager;
}
