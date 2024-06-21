package com.capstone.realmen.controller.api.collection.assignment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.realmen.common.response.ListResponse;
import com.capstone.realmen.controller.api.collection.assignment.models.ServiceAssignmentResponse;

@RequestMapping("/collection/service-assignment")
public interface IServiceAssignmentAPI {
    @GetMapping
    ListResponse<ServiceAssignmentResponse> findBy(
            @RequestParam(required = false, value = "search", defaultValue = "") String search);
}
