package com.capstone.realmen.data.dao.shop.service;

public interface ShopServiceDAO {
    void setShopServiceId(Long shopServiceId);
    Long getShopServiceId();

    void setShopServiceName(String shopServiceName);
    String getShopServiceName();

    void setShopServicePrice(Long shopServicePrice);
    Long getShopServicePrice();

    void setShopServiceThumbnail(String shopServiceThumbnail);
    String getShopServiceThumbnail();

    void setShopCategoryId(Long shopCategoryId);
    Long getShopCategoryId();

    void setShopCategoryCode(String shopCategoryCode);
    String getShopCategoryCode();


    void setShopCategoryName(String shopCategoryName);
    String getShopCategoryName();

    
}
