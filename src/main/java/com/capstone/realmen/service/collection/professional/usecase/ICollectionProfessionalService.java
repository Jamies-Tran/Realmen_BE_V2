package com.capstone.realmen.service.collection.professional.usecase;

import java.util.List;

import com.capstone.realmen.data.dto.account.professional.Professional;
import com.capstone.realmen.service.collection.professional.data.ProfessionalSearchByField;

public interface ICollectionProfessionalService {
    List<Professional> collectionGetProfessionalList(ProfessionalSearchByField searchByField);
}
