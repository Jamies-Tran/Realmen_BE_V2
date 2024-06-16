package com.capstone.realmen.controller.api.collection.location;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.realmen.common.response.ListResponse;

@RequestMapping("/collection/location")
public interface ILocationAPI {

    @GetMapping
    ListResponse<String> getLocationList(
        @RequestParam(value = "input") String search);
}
