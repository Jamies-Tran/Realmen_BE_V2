package com.capstone.realmen.controller.api.admin.authentication.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dto.access.token.AccessToken;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdminAccessTokenMapper {
    AdminAccessTokenResponse toModel(AccessToken dto);

    AccessToken toDto(AdminAccessTokenRequest model);
}
