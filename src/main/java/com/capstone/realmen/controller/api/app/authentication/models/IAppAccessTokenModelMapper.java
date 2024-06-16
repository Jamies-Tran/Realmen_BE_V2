package com.capstone.realmen.controller.api.app.authentication.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dto.access.token.AccessToken;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAppAccessTokenModelMapper {
    AccessToken toDto(AppAccessTokenRequest model);

    AppAccessTokenResponse toModel(AccessToken dto);

    
}
