package com.capstone.realmen.service.shop.service;

import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.RequestContext;
import com.capstone.realmen.data.dto.shop.service.ShopServiceMapper;
import com.capstone.realmen.repository.database.audit.Auditable;
import com.capstone.realmen.repository.database.shop.service.ShopServiceEntity;
import com.capstone.realmen.repository.database.shop.service.ShopServiceRepository;
import com.capstone.realmen.service.shop.service.data.ShopServiceCreateRequire;
import com.capstone.realmen.service.shop.service.others.display.ServiceDisplayCommandService;
import com.capstone.realmen.service.shop.service.others.display.data.ServiceDisplayCreateRequire;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class ShopServiceCommandService {
    @NonNull
    ShopServiceRepository shopServiceRepository;
    @NonNull
    ServiceDisplayCommandService serviceDisplayCommandService;
    @NonNull
    RequestContext requestContext;
    @NonNull
    ShopServiceMapper shopServiceMapper;

    public void create(ShopServiceCreateRequire createRequire) {
        ShopServiceEntity saveShopService = shopServiceRepository.save(
            shopServiceMapper.toEntity(createRequire.shopService())
                .withAudit(Auditable.ofCreated(requestContext.getAccount()))
        );
        serviceDisplayCommandService.create(
            ServiceDisplayCreateRequire.builder()
                .shopServiceId(saveShopService.getShopServiceId())
                .serviceDisplays(createRequire.serviceDisplays())
                .build()
        );
    }
}
