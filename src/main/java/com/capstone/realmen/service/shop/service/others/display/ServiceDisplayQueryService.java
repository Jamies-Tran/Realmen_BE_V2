package com.capstone.realmen.service.shop.service.others.display;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.realmen.data.dto.shop.service.display.ServiceDisplay;
import com.capstone.realmen.data.dto.shop.service.display.ServiceDisplayMapper;
import com.capstone.realmen.repository.database.shop.service.display.ServiceDisplayRepository;
import com.capstone.realmen.service.shop.service.others.display.data.ServiceDisplaySearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceDisplayQueryService {
    @NonNull
    ServiceDisplayRepository serviceDisplayRepository;
    @NonNull
    ServiceDisplayMapper serviceDisplayMapper;

    public List<ServiceDisplay> findByShopServiceId(ServiceDisplaySearchByField searchByField) {
        return serviceDisplayRepository
                .findAllByShopServiceId(searchByField.shopServiceId())
                .stream()
                .map(serviceDisplayMapper::toDto)
                .toList();
    }
}
