package com.capstone.realmen.repository.database.branch.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.realmen.repository.database.branch.service.dao.BranchServiceDAO;

@Repository
public interface IBranchServiceRepository extends JpaRepository<BranchServiceEntity, Long>{

    @Query("""
            SELECT 
                bs.branchServiceId AS branchServiceId,
                bs.shopServiceId AS shopServiceId,
                bs.branchId AS branchId,
                bs.branchServiceStatusCode AS branchServiceStatusCode,
                bs.branchServiceStatusName AS branchServiceStatusName,
                sc.serviceAssignmentCode AS serviceAssignmentCode,
                sc.serviceAssignmentName AS serviceAssignmentName
            FROM BranchServiceEntity bs
            INNER JOIN ShopServiceEntity ss ON bs.shopServiceId = ss.shopServiceId
            INNER JOIN ShopCategoryEntity sc ON ss.shopCategoryId = sc.shopCategoryId
            WHERE bs.branchId = :branchId
                AND bs.branchServiceStatusCode = :statusCode
            """)
    List<BranchServiceDAO> findAllByBranchIdAndStatusCode(Long branchId, String statusCode);
}
