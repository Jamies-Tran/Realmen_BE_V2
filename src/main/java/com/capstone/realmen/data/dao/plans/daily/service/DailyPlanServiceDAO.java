package com.capstone.realmen.data.dao.plans.daily.service;

public interface DailyPlanServiceDAO {
    void setDailyPlanServiceId(Long dailyPlanServiceId);

    Long getDailyPlanServiceId();

    void setDailyPlanId(Long dailyPlanId);

    Long getDailyPlanId();

    void setWeeklyPlanId(Long weeklyPlanId);

    Long getWeeklyPlanId();

    void setBranchId(Long branchId);

    Long getBranchId();

    void setShopServiceId(Long shopServiceId);

    Long getShopServiceId();

    void setShopServiceName(String shopServiceName);

    String getShopServiceName();

    void setBranchServicePrice(Long branchServicePrice);

    Long getBranchServicePrice();

    void setShopServicePrice(Long shopServicePrice);

    Long getShopServicePrice();

    void setCategoryCode(String categoryCode);

    String getCategoryCode();

    void setCategoryName(String categoryName);

    String getCategoryName();

    void setServiceAssignmentCode(String serviceAssignmentCode);

    String getServiceAssignmentCode();

    void setServiceAssignmentName(String serviceAssignmentName);

    String getServiceAssignmentName();

    void setEstimateDuration(Integer estimateDuration);

    Integer getEstimateDuration();

    void setDurationUnitCode(String durationUnitCode);

    String getDurationUnitCode();

    void setDurationUnitName(String durationUnitName);

    String getDurationUnitName();

    void setBranchServiceId(Long branchServiceId);

    Long getBranchServiceId();
}
