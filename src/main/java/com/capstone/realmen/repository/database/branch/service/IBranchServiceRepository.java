package com.capstone.realmen.repository.database.branch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.realmen.data.dao.branch.service.BranchServiceDAO;
import com.capstone.realmen.service.branch.others.services.data.BranchServiceSearchCriteria;

@Repository
public interface IBranchServiceRepository extends JpaRepository<BranchServiceEntity, Long> {

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

    @Query("""
            SELECT
                bs.branchServiceId AS branchServiceId,
                bs.shopServiceId AS shopServiceId,
                bs.branchId AS branchId,
                ss.shopServiceName AS shopServiceName,
                ss.shopServiceThumbnail AS thumbnail,
                bs.branchServiceStatusCode AS branchServiceStatusCode,
                bs.branchServiceStatusName AS branchServiceStatusName,
                sc.serviceAssignmentCode AS serviceAssignmentCode,
                sc.serviceAssignmentName AS serviceAssignmentName,
                sc.shopCategoryCode AS shopCategoryCode,
                sc.shopCategoryName AS shopCategoryName,
                ss.shopServicePrice AS shopServicePrice,
                bs.branchServicePrice AS branchServicePrice,
                bs.estimateDuration AS estimateDuration,
                bs.durationUnitCode AS durationUnitCode,
                bs.durationUnitName AS durationUnitName
            FROM BranchServiceEntity bs
            INNER JOIN ShopServiceEntity ss ON bs.shopServiceId = ss.shopServiceId
            INNER JOIN ShopCategoryEntity sc ON ss.shopCategoryId = sc.shopCategoryId
            WHERE bs.branchId = :#{#searchCriteria.branchId()}
                AND (:#{#searchCriteria.hasSearchEmpty()} = TRUE
                    OR LOWER(ss.shopServiceName) = :#{#searchCriteria.search()})
                AND (:#{#searchCriteria.hasPriceRangeEmpty()} = TRUE
                    OR bs.branchServicePrice BETWEEN :#{#searchCriteria.priceRange().get(0)} AND :#{#searchCriteria.priceRange().get(1)})
                AND (:#{#searchCriteria.hasShopCategoryIdEmpty()} = TRUE
                    OR sc.shopCategoryId = :#{#searchCriteria.shopCategoryId()})
                AND (:#{#searchCriteria.hasAssignmentTypeCodeEmpty()} = TRUE
                    OR sc.serviceAssignmentCode = :#{#searchCriteria.assignmentTypeCode()})
                AND (:#{#searchCriteria.hasServiceIdEmpty()} = TRUE
                    OR bs.shopServiceId IN :#{#searchCriteria.serviceIds()})
            """)
    Page<BranchServiceDAO> findAll(BranchServiceSearchCriteria searchCriteria, Pageable pageable);

    @Query("""
            SELECT
                bs.branchServiceId AS branchServiceId,
                bs.shopServiceId AS shopServiceId,
                bs.branchId AS branchId,
                ss.shopServiceName AS shopServiceName,
                ss.shopServiceThumbnail AS thumbnail,
                bs.branchServiceStatusCode AS branchServiceStatusCode,
                bs.branchServiceStatusName AS branchServiceStatusName,
                sc.serviceAssignmentCode AS serviceAssignmentCode,
                sc.serviceAssignmentName AS serviceAssignmentName,
                sc.shopCategoryCode AS shopCategoryCode,
                sc.shopCategoryName AS shopCategoryName,
                ss.shopServicePrice AS shopServicePrice,
                bs.branchServicePrice AS branchServicePrice,
                bs.estimateDuration AS estimateDuration,
                bs.durationUnitCode AS durationUnitCode,
                bs.durationUnitName AS durationUnitName
            FROM BranchServiceEntity bs
            INNER JOIN ShopServiceEntity ss ON bs.shopServiceId = ss.shopServiceId
            INNER JOIN ShopCategoryEntity sc ON ss.shopCategoryId = sc.shopCategoryId
            WHERE bs.branchId = :branchId
                AND bs.shopServiceId = :serviceId
            """)
    Optional<BranchServiceDAO> findByBranchIdAndServiceId(Long branchId, Long serviceId);

    @Query("""
            SELECT
                bs.branchServiceId AS branchServiceId,
                bs.shopServiceId AS shopServiceId,
                bs.branchId AS branchId,
                ss.shopServiceName AS shopServiceName,
                ss.shopServiceThumbnail AS thumbnail,
                bs.branchServiceStatusCode AS branchServiceStatusCode,
                bs.branchServiceStatusName AS branchServiceStatusName,
                sc.serviceAssignmentCode AS serviceAssignmentCode,
                sc.serviceAssignmentName AS serviceAssignmentName,
                sc.shopCategoryCode AS shopCategoryCode,
                sc.shopCategoryName AS shopCategoryName,
                ss.shopServicePrice AS shopServicePrice,
                bs.branchServicePrice AS branchServicePrice,
                bs.estimateDuration AS estimateDuration,
                bs.durationUnitCode AS durationUnitCode,
                bs.durationUnitName AS durationUnitName
            FROM BranchServiceEntity bs
            INNER JOIN ShopServiceEntity ss ON bs.shopServiceId = ss.shopServiceId
            INNER JOIN ShopCategoryEntity sc ON ss.shopCategoryId = sc.shopCategoryId
            WHERE bs.branchId = :branchId
                AND bs.shopServiceId IN :serviceIds
            """)
    List<BranchServiceDAO> findByBranchIdAndServiceIds(Long branchId, List<Long> serviceIds);
}
