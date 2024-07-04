package com.capstone.realmen.service.collection.professional;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.realmen.data.dto.account.professional.Professional;
import com.capstone.realmen.service.collection.professional.data.ProfessionalSearchByField;
import com.capstone.realmen.service.collection.professional.usecase.ICollectionProfessionalService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfessionalUseCaseService implements ICollectionProfessionalService{
    @NonNull
    ProfessionalQueryService professionalQueryService;

    @Override
    public List<Professional> collectionGetProfessionalList(ProfessionalSearchByField searchByField) {
        return professionalQueryService.findAll(searchByField);
    }

    
}
