package com.capstone.realmen.repository.database.branch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.realmen.service.branch.data.BranchSearchCriteria;

@Repository
public interface IBranchRepository extends JpaRepository<BranchEntity, Long> {

    @Query("""
            SELECT b
            FROM BranchEntity b
            WHERE :#{#searchCriteria.hasSearchEmpty()} = TRUE
                OR LOWER(b.branchName) LIKE %:#{#searchCriteria.search()}% 
                OR LOWER(b.branchDistrict) LIKE %:#{#searchCriteria.search()}%
                OR LOWER(b.branchWard) LIKE %:#{#searchCriteria.search()}%
                OR LOWER(b.branchProvince) LIKE %:#{#searchCriteria.search()}% 
            AND (b.branchStatusCode IN :#{#searchCriteria.defaulStatusCodes()})
            """)
    Page<BranchEntity> findAll(BranchSearchCriteria searchCriteria, Pageable pageable);
}
