package com.capstone.realmen.service.collection.assignment;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.capstone.realmen.common.enums.EServiceAssignment;
import com.capstone.realmen.data.dto.shop.category.assignment.ServiceAssignment;
import com.capstone.realmen.service.collection.assignment.data.ServiceAssignmentSearchByField;

@Service
public class ServiceAssignmentQueryService {
    public List<ServiceAssignment> findBy(ServiceAssignmentSearchByField searchByField) {
        return Arrays.stream(EServiceAssignment.values())
                .filter(serviceAssignment -> !StringUtils.hasText(searchByField.name())
                        || serviceAssignment.getName().toLowerCase().contains(searchByField.name()))
                .map(serviceAssignment -> ServiceAssignment.builder()
                        .code(serviceAssignment.getCode())
                        .name(serviceAssignment.getName())
                        .professional(serviceAssignment.getProfessional().toDto())
                        .build())
                .toList();
    }
}
