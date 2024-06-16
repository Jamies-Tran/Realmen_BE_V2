package com.capstone.realmen.service.collection.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.capstone.realmen.service.collection.location.data.LocationSearchByField;
import com.capstone.realmen.service.collection.location.usecase.ICollectionLocationService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LocationUseCaseService implements ICollectionLocationService {
    @NonFinal
    @Value("${goong.api.key}")
    private String apiKey;
    @NonNull
    LocationQueryService locationQueryService;

    @Override
    public List<String> collectionGetLocationList(LocationSearchByField searchByField) {
        return locationQueryService.getLocation(searchByField.withApiKey(apiKey));
    }
}
