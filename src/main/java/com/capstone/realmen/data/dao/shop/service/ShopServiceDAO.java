package com.capstone.realmen.data.dao.shop.service;

public interface ShopServiceDAO {
    void setShopServiceId(Long shopServiceId);

    Long getShopServiceId();

    void setBranchId(Long branchId);

    Long getBranchId();

    void setShopServiceName(String shopServiceName);

    String getShopServiceName();

    void setShopServicePrice(Long shopServicePrice);

    Long getShopServicePrice();

    void setBranchServicePrice(Long branchServicePrice);

    Long getBranchServicePrice();

    void setShopServiceThumbnail(String shopServiceThumbnail);

    String getShopServiceThumbnail();

    void setShopCategoryId(Long shopCategoryId);

    Long getShopCategoryId();

    void setShopCategoryCode(String shopCategoryCode);

    String getShopCategoryCode();

    void setShopCategoryName(String shopCategoryName);

    String getShopCategoryName();

    void setServiceAssignmentCode(String serviceAssignmentCode);

    String getServiceAssignmentCode();

    void setEstimateDuration(Integer estimateDuration);

    Integer getEstimateDuration();

    void setDurationUnitCode(String durationUnitCode);

    String getDurationUnitCode();

    void setDurationUnitName(String durationUnitName);

    String getDurationUnitName();
}
