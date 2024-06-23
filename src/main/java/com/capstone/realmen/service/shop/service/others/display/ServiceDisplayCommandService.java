package com.capstone.realmen.service.shop.service.others.display;

import org.springframework.stereotype.Service;

import com.capstone.realmen.data.dto.shop.service.display.ServiceDisplayMapper;
import com.capstone.realmen.repository.database.shop.service.display.ServiceDisplayRepository;
import com.capstone.realmen.service.shop.service.others.display.data.ServiceDisplayCreateRequire;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceDisplayCommandService {
    @NonNull
    ServiceDisplayRepository serviceDisplayRepository;
    @NonNull
    ServiceDisplayMapper serviceDisplayMapper;

    public void create(ServiceDisplayCreateRequire createRequire) {
        serviceDisplayRepository.saveAll(
                createRequire.serviceDisplays().stream()
                        .map(serviceDisplayMapper::toEntity)
                        .toList());
    }
}
