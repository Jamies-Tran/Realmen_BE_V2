package com.capstone.realmen.repository.database.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    @Query("""
            SELECT a
            FROM AccountEntity a
            WHERE a.phone = :search OR a.staffCode = :search
            """)
    Optional<AccountEntity> findByPhoneOrStaffCode(String search);
}
