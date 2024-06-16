package com.capstone.realmen.service.collection.location.usecase;

import java.util.List;

import com.capstone.realmen.service.collection.location.data.LocationSearchByField;

public interface ICollectionLocationService {
    List<String> collectionGetLocationList(LocationSearchByField searchByField);
}
