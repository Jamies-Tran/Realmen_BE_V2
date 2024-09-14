package com.capstone.realmen.controller.api.admin.plans.daily.models;

import lombok.Builder;
import java.util.List;

@Builder
public record DailyPlanRequest(
        List<DailyPlanServiceRequest> services,
        List<DailyPlanAccountRequest> staffs) {

}
