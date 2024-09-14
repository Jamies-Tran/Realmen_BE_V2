package com.capstone.realmen.common.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public record PageRequestCustom(Pageable pageRequest) {
    public static PageRequestCustom of(Integer current, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(current - 1, pageSize);
        return new PageRequestCustom(pageRequest);
    }

    public static PageRequestCustom of(Integer current, Integer pageSize, Sort sortBy) {
        PageRequest pageRequest = PageRequest.of(current - 1, pageSize, sortBy);
        return new PageRequestCustom(pageRequest);
    }

    public static PageRequestCustom unPaged() {
        return new PageRequestCustom(Pageable.unpaged());
    }

    public Integer current() {
        return pageRequest.getPageNumber() + 1;
    }
}
