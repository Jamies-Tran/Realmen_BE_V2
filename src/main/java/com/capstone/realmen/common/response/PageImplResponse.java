package com.capstone.realmen.common.response;

import java.util.List;

public record PageImplResponse<T>(
                List<T> content,
                Long totalElement,
                Integer totalPage,
                Integer currentPage,
                Integer pageSize) {
}
