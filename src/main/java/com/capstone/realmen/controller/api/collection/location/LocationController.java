package com.capstone.realmen.controller.api.collection.location;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.response.ListResponse;
import com.capstone.realmen.service.collection.location.LocationUseCaseService;
import com.capstone.realmen.service.collection.location.data.LocationSearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LocationController implements ILocationAPI {
    @NonNull
    LocationUseCaseService locationUseCaseService;
    
    @Override
    public ListResponse<String> getLocationList(String search) {
        return new ListResponse<>(
            locationUseCaseService.collectionGetLocationList(
                LocationSearchByField.builder()
                    .search(search)
                    .build())
        );
    }
    
}
