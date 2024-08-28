package com.capstone.realmen.common.request;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public record CustomSort(Sort sort) {
    public static Sort of(String sorter) {
        List<String> sorts = Arrays.stream(sorter.split("_")).toList();
        if (sorts.size() == 1) {
            return Sort.by(Direction.ASC, sorts.get(0));
        } else {
            String sortDirection = sorts.get(1).toUpperCase();
            switch (sortDirection) {
                case "ASC":
                    return Sort.by(Direction.ASC, sorts.get(0));
                case "DESC":
                    return Sort.by(Direction.DESC, sorts.get(0));

                default:
                    return null;
            }
        }
    }
}
