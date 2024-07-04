package com.capstone.realmen.service.collection.professional;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.realmen.common.enums.EProfessional;
import com.capstone.realmen.data.dto.account.professional.Professional;
import com.capstone.realmen.service.collection.professional.data.ProfessionalSearchByField;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfessionalQueryService {
    
    public List<Professional> findAll(ProfessionalSearchByField searchByField) {
        return EProfessional.findAll(searchByField.name());
    }
}
