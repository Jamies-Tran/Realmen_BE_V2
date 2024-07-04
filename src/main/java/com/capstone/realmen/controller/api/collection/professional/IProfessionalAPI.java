package com.capstone.realmen.controller.api.collection.professional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.realmen.common.response.ListResponse;
import com.capstone.realmen.controller.api.collection.professional.models.ProfessionalResponse;

@RequestMapping("/collection/professional")
public interface IProfessionalAPI {
    @GetMapping
    ListResponse<ProfessionalResponse> findAll(
            @RequestParam(required = false, value = "name", defaultValue = "") String name);
}
