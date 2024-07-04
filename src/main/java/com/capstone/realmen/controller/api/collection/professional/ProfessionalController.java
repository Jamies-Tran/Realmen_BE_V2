package com.capstone.realmen.controller.api.collection.professional;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.response.ListResponse;
import com.capstone.realmen.controller.api.collection.professional.models.IProfessionalModelMapper;
import com.capstone.realmen.controller.api.collection.professional.models.ProfessionalResponse;
import com.capstone.realmen.service.collection.professional.ProfessionalUseCaseService;
import com.capstone.realmen.service.collection.professional.data.ProfessionalSearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfessionalController implements IProfessionalAPI {
    @NonNull
    ProfessionalUseCaseService professionalUseCaseService;

    @NonNull
    IProfessionalModelMapper modelMapper;

    @Override
    public ListResponse<ProfessionalResponse> findAll(String name) {
        return new ListResponse<>(
                professionalUseCaseService.collectionGetProfessionalList(
                        ProfessionalSearchByField.builder()
                                .name(name)
                                .build())
                        .stream()
                        .map(modelMapper::toModel)
                        .toList());
    }

}
