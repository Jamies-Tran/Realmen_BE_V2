package com.capstone.realmen.service.plans.others.daily.plan.others.service.data;

import java.util.List;

import com.capstone.realmen.data.dto.branch.service.BranchService;
import com.capstone.realmen.data.dto.plans.daily.service.DailyPlanService;

import lombok.Builder;

@Builder
public record DailyPlanServiceCreateRequire(
        List<DailyPlanService> dailyPlanServices,
        List<BranchService> branchServices,
        List<Long> dailyPlanIds) {

}
