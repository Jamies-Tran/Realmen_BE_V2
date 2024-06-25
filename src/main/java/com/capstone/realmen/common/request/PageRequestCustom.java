package com.capstone.realmen.common.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public record PageRequestCustom(PageRequest pageRequest) {
    public static PageRequestCustom of(Integer current, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(current - 1, pageSize, Sort.by("updatedAt").descending());
        return new PageRequestCustom(pageRequest);
    }

    public static PageRequestCustom of(Integer current, Integer pageSize, Sort sortBy) {
        PageRequest pageRequest = PageRequest.of(current - 1, pageSize, sortBy);
        return new PageRequestCustom(pageRequest);
    }

    public Integer current() {
        return pageRequest.getPageNumber() + 1;
    }
}
