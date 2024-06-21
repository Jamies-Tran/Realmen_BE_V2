package com.capstone.realmen.service.collection.assignment.usecase;

import java.util.List;

import com.capstone.realmen.data.dto.shop.category.assignment.ServiceAssignment;
import com.capstone.realmen.service.collection.assignment.data.ServiceAssignmentSearchByField;

public interface ICollectionServiceAssignmentService {
    List<ServiceAssignment> collectionGetServiceAssignmentList(ServiceAssignmentSearchByField searchByField);
}
