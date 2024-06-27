package com.capstone.realmen.service.branch.others.address;

import org.springframework.stereotype.Service;

import com.capstone.realmen.data.dto.branch.address.Address;
import com.capstone.realmen.repository.feign.geo.IGeometryPlaceHolder;
import com.capstone.realmen.repository.feign.geo.data.GoongAddressResponse;
import com.capstone.realmen.service.branch.others.address.data.AddressSearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddressQueryService {
    @NonNull
    IGeometryPlaceHolder geometryPlaceHolder;

    public Address getAddress(AddressSearchByField searchByField) {
        GoongAddressResponse addressResponse = geometryPlaceHolder
            .getGoongAddress(searchByField.apiKey(), searchByField.address());
        return Address.of(addressResponse);
    }
}
