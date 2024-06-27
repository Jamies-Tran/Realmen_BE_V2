package com.capstone.realmen.data.dto.branch.address;

import java.util.List;

import com.capstone.realmen.repository.feign.geo.data.AddressComponent;
import com.capstone.realmen.repository.feign.geo.data.Geometry;
import com.capstone.realmen.repository.feign.geo.data.GoongAddress;
import com.capstone.realmen.repository.feign.geo.data.GoongAddressResponse;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record Address(
    String branchStreet,
    String branchWard,
    String branchDistrict,
    String branchProvince,
    Double latitude,
    Double longitude
) {
    public static Address of(GoongAddressResponse goongAddressResponse) {
        List<AddressComponent> addressComponents = goongAddressResponse.results()
            .stream()
            .map(GoongAddress::addressComponents)
            .findAny()
            .get();
        Geometry geometry = goongAddressResponse.results()
            .stream()
            .map(GoongAddress::geometry)
            .findAny()
            .get();
        return Address.builder()
            .branchStreet(addressComponents.get(0).addressComponent())
            .branchWard(addressComponents.get(1).addressComponent())
            .branchDistrict(addressComponents.get(2).addressComponent())
            .branchProvince(addressComponents.get(3).addressComponent())
            .latitude(geometry.location().latitude())
            .longitude(geometry.location().longitude())
            .build();
    }
}
