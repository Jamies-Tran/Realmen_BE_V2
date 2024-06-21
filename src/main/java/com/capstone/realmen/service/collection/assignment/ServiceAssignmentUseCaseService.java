package com.capstone.realmen.service.collection.assignment;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.realmen.data.dto.shop.category.assignment.ServiceAssignment;
import com.capstone.realmen.service.collection.assignment.data.ServiceAssignmentSearchByField;
import com.capstone.realmen.service.collection.assignment.usecase.ICollectionServiceAssignmentService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceAssignmentUseCaseService implements ICollectionServiceAssignmentService {
    @NonNull
    ServiceAssignmentQueryService assignmentQueryService;

    @Override
    public List<ServiceAssignment> collectionGetServiceAssignmentList(ServiceAssignmentSearchByField searchByField) {
        return assignmentQueryService.findBy(searchByField);
    }

}
