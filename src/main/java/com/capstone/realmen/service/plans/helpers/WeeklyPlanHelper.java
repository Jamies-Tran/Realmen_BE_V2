package com.capstone.realmen.service.plans.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.capstone.realmen.data.dto.plans.daily.DailyPlan;

public class WeeklyPlanHelper {
    protected String generateWeeklyPlanName(List<DailyPlan> dailyPlans) {
        if (dailyPlans.isEmpty()) {
            return "Kế hoạch hoạt đông (Bản nháp)";
        }

        List<LocalDate> sortedByDate = dailyPlans.stream()
                .map(DailyPlan::date)
                .sorted()
                .toList();
        String from = sortedByDate
                .get(0)
                .format(DateTimeFormatter.ofPattern("dd/MM"));
        String to = sortedByDate
                .get(sortedByDate.size() - 1)
                .format(DateTimeFormatter.ofPattern("dd/MM"));
        return "Kế hoạch hoạt đông (%s - %s)".formatted(from, to);
    }
}
