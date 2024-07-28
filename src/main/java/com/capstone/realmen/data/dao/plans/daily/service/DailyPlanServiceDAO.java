package com.capstone.realmen.data.dao.plans.daily.service;

public interface DailyPlanServiceDAO {
    void setDailyPlanServiceId(Long dailyPlanServiceId);

    Long getDailyPlanServiceId();

    void setDailyPlanId(Long dailyPlanId);

    Long getDailyPlanId();

    void setShopServiceId(Long shopServiceId);

    Long getShopServiceId();

    void setShopServiceName(String shopServiceName);

    String getShopServiceName();

    void setShopServicePrice(Long shopServicePrice);

    Long getShopServicePrice();

    void setCategoryCode(String categoryCode);

    String getCategoryCode();

    void setCategoryName(String categoryName);

    String getCategoryName();
}
