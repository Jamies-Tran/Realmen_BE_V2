package com.capstone.realmen.repository.database.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.realmen.data.dao.account.AccountDAO;
import com.capstone.realmen.service.account.data.AccountSearchCriteria;

@Repository
public interface IAccountRepository extends JpaRepository<AccountEntity, Long> {
        @Query("""
                        SELECT
                            a.accountId AS accountId,
                            a.password AS password,
                            ab.branchId AS branchId,
                            a.firstName AS firstName,
                            a.lastName AS lastName,
                            a.phone AS phone,
                            a.address AS address,
                            a.staffCode AS staffCode,
                            a.professionalTypeCode AS professionalTypeCode,
                            a.professionalTypeName AS professionalTypeName,
                            a.roleCode AS roleCode,
                            a.roleName AS roleName,
                            a.thumbnail AS thumbnail,
                            a.dob AS dob,
                            a.genderCode AS genderCode,
                            a.genderName AS genderName,
                            a.accountStatusCode AS accountStatusCode,
                            a.accountStatusName AS accountStatusName
                        FROM AccountEntity a
                        LEFT JOIN AccountBranchEntity ab ON a.accountId = ab.accountId
                        WHERE a.phone = :search OR a.staffCode = :search
                        """)
        Optional<AccountDAO> findByPhoneOrStaffCode(String search);

        @Query("""
                        SELECT
                            a.accountId AS accountId,
                            ab.branchId AS branchId,
                            a.firstName AS firstName,
                            a.lastName AS lastName,
                            a.phone AS phone,
                            a.address AS address,
                            a.staffCode AS staffCode,
                            a.professionalTypeCode AS professionalTypeCode,
                            a.professionalTypeName AS professionalTypeName,
                            a.roleCode AS roleCode,
                            a.roleName AS roleName,
                            a.thumbnail AS thumbnail,
                            a.dob AS dob,
                            a.genderCode AS genderCode,
                            a.genderName AS genderName,
                            a.accountStatusCode AS accountStatusCode,
                            a.accountStatusName AS accountStatusName
                        FROM AccountEntity a
                        LEFT JOIN AccountBranchEntity ab ON a.accountId = ab.accountId
                        WHERE (:#{#searchCriteria.hasSearchEmpty()} = TRUE
                            OR (LOWER(CONCAT(a.firstName, ' ', a.lastName)) LIKE '%'||:#{#searchCriteria.search()}||'%'
                            OR a.phone LIKE '%'||:#{#searchCriteria.search()}||'%'
                            OR LOWER(a.staffCode) LIKE '%'||:#{#searchCriteria.search()}||'%'))
                        AND (:#{#searchCriteria.hasStatusEmpty()} = TRUE
                                OR a.accountStatusCode IN (:defaultStatusCodes))
                        AND (:#{#searchCriteria.hasProfessionalTypeCodeEmpty()} = TRUE
                                OR a.professionalTypeCode IN (:#{#searchCriteria.professionalTypeCodes()}))
                        AND (:#{#searchCriteria.hasRoleEmpty()} = TRUE
                                OR a.roleCode IN :#{#searchCriteria.roles()})
                        AND (:#{#searchCriteria.hasBranchIdEmpty()} = TRUE
                                OR ab.branchId = :#{#searchCriteria.branchId()})
                        """)
        Page<AccountDAO> findAll(AccountSearchCriteria searchCriteria, List<String> defaultStatusCodes,
                        Pageable pageable);

        @Query("""
                                SELECT
                                        a.accountId AS accountId,
                                        a.password AS password,
                                        ab.branchId AS branchId,
                                        a.firstName AS firstName,
                                        a.lastName AS lastName,
                                        a.phone AS phone,
                                        a.address AS address,
                                        a.staffCode AS staffCode,
                                        a.professionalTypeCode AS professionalTypeCode,
                                        a.professionalTypeName AS professionalTypeName,
                                        a.roleCode AS roleCode,
                                        a.roleName AS roleName,
                                        a.thumbnail AS thumbnail,
                                        a.dob AS dob,
                                        a.genderCode AS genderCode,
                                        a.genderName AS genderName,
                                        a.accountStatusCode AS accountStatusCode,
                                        a.accountStatusName AS accountStatusName
                                FROM AccountEntity a
                                LEFT JOIN AccountBranchEntity ab ON a.accountId = ab.accountId
                                WHERE a.accountId IN :accountIds
                        """)
        List<AccountDAO> findAllByIds(List<Long> accountIds);

        Boolean existsByStaffCodeOrPhone(String staffCode, String phone);

        Boolean existsByPhone(String phone);
}
