package com.capstone.realmen.controller.api.collection.assignment;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.response.ListResponse;
import com.capstone.realmen.controller.api.collection.assignment.models.IServiceAssignmentModelMapper;
import com.capstone.realmen.controller.api.collection.assignment.models.ServiceAssignmentResponse;
import com.capstone.realmen.service.collection.assignment.ServiceAssignmentUseCaseService;
import com.capstone.realmen.service.collection.assignment.data.ServiceAssignmentSearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceAssignmentController implements IServiceAssignmentAPI{
    @NonNull
    ServiceAssignmentUseCaseService serviceAssignmentUseCaseService;
    @NonNull
    IServiceAssignmentModelMapper modelMapper;

    @Override
    public ListResponse<ServiceAssignmentResponse> findBy(String search) {
        return new ListResponse<>(
            serviceAssignmentUseCaseService
                .collectionGetServiceAssignmentList(ServiceAssignmentSearchByField.of(search))
                .stream()
                .map(modelMapper::toModel)
                .toList()
        );
    }
}
