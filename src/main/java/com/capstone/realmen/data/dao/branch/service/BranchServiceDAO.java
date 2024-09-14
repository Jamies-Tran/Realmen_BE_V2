package com.capstone.realmen.data.dao.branch.service;

public interface BranchServiceDAO {
    void setBranchServiceId(Long branchServiceId);

    Long getBranchServiceId();

    void setBranchId(Long branchId);

    Long getBranchId();

    void setShopServiceId(Long shopServiceId);

    Long getShopServiceId();

    void setBranchServiceStatusCode(String branchServiceStatusCode);

    String getBranchServiceStatusCode();

    void setBranchServiceStatusName(String branchServiceStatusName);

    String getBranchServiceStatusName();

    void setServiceAssignmentCode(String serviceAssignmentCode);

    String getServiceAssignmentCode();

    void setServiceAssignmentName(String serviceAssignmentName);

    String getServiceAssignmentName();

    void setShopServicePrice(Long shopServicePrice);

    Long getShopServicePrice();

    void setBranchServicePrice(Long branchServicePrice);

    Long getBranchServicePrice();

    void setShopServiceName(String shopServiceName);

    String getShopServiceName();

    void setShopServiceThumbnail(String shopServiceThumbnail);

    String getShopServiceThumbnail();

    void setEstimateDuration(Integer estimateDuration);

    Integer getEstimateDuration();

    void setDurationUnitCode(String durationUnitCode);

    String getDurationUnitCode();

    void setDurationUnitName(String durationUnitName);

    String getDurationUnitName();

    void setShopCategoryCode(String shopCategoryCode);

    String getShopCategoryCode();
    
    void setShopCategoryName(String shopCategoryName);

    String getShopCategoryName();
}
