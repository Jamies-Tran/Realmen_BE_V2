package com.capstone.realmen.repository.database.account;

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
@Table(name = "account")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountId;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "phone")
    String phone;
    @Column(name = "address")
    String address;
    @Column(name = "password")
    String password;
    @Column(name = "staff_code")
    String staffCode;
    @Column(name = "professional_type_code")
    String professionalTypeCode;
    @Column(name = "professional_type_name")
    String professionalTypeName;
    @Column(name = "role_code")
    String roleCode;
    @Column(name = "role_name")
    String roleName;
    @Column(name = "thumbnail", columnDefinition = "LONGTEXT")
    String thumbnail;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    LocalDate dob;
    @Column(name = "gender_code")
    String genderCode;
    @Column(name = "gender_name")
    String genderName;
    @Column(name = "account_status_code")
    String accountStatusCode;
    @Column(name = "account_status_name")
    String accountStatusName;


    public AccountEntity setAudit(Auditable auditable) {
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

    public AccountEntity withStatus(String statusCode, String statusName) {
        return this.withAccountStatusCode(statusCode).withAccountStatusName(statusName);
    }

    public AccountEntity withRole(String roleCode, String roleName) {
        return this.withRoleCode(roleCode).withRoleName(roleName);
    }
}
