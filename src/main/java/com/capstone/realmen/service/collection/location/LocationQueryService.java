package com.capstone.realmen.service.collection.location;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.realmen.repository.feign.location.ILocationPlaceHolder;
import com.capstone.realmen.repository.feign.location.data.LocationPrediction;
import com.capstone.realmen.service.collection.location.data.LocationSearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LocationQueryService {
    @NonNull
    ILocationPlaceHolder locationPlaceHolder;

    public List<String> getLocation(LocationSearchByField searchByField) {
        return locationPlaceHolder
            .getAddress(searchByField.apiKey(), searchByField.search())
            .locationList()
            .stream()
            .map(LocationPrediction::location)
            .toList();
    }
}
