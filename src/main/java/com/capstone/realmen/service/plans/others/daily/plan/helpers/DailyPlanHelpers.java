package com.capstone.realmen.service.plans.others.daily.plan.helpers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.capstone.realmen.common.util.DateTimeHandler;
import com.capstone.realmen.data.dto.plans.daily.account.DailyPlanAccount;
import com.capstone.realmen.data.dto.plans.daily.service.DailyPlanService;

public class DailyPlanHelpers {

    protected List<LocalDateTime> getDailyPlanDateList(LocalDateTime pickupDate) {
        List<LocalDateTime> firstAndLast = DateTimeHandler.firstAndLast(pickupDate);
        LocalDateTime firstDate = firstAndLast.get(0);
        LocalDateTime lastDate = firstAndLast.get(1);
        List<LocalDateTime> dailyInWeek = new ArrayList<>();
        while (firstDate.compareTo(lastDate) <= 0) {
            dailyInWeek.add(firstDate);
            firstDate = plusDay(firstDate, 1);
        }

        return dailyInWeek.stream().sorted().toList();
    }

    protected List<LocalDateTime> dateListAfterWeek(List<LocalDateTime> createdDate) {
        return createdDate.stream()
                .map(date -> DateTimeHandler.dayNextWeek(date))
                .toList();
    }

    protected LocalDateTime getEquivalentWeeklyDate(LocalDateTime origin, LocalDateTime reference) {
        List<LocalDateTime> firstAndLast = DateTimeHandler.firstAndLast(reference);
        LocalDateTime lastDate = firstAndLast.get(1);
        boolean keepForward = true;
        LocalDateTime pickUpDate = origin;
        while (keepForward) {
            pickUpDate = DateTimeHandler.dayNextWeek(pickUpDate);
            keepForward = DateTimeHandler.dayNextWeek(pickUpDate).compareTo(lastDate) <= 0;
        }

        return pickUpDate;
    }

    protected Map<Long, List<DailyPlanAccount>> daGroupingBy(List<DailyPlanAccount> dailyPlanAccounts) {
        return dailyPlanAccounts.stream().collect(Collectors.groupingBy(DailyPlanAccount::dailyPlanId));
    }

    protected Map<Long, List<DailyPlanService>> dsGroupingBy(List<DailyPlanService> dailyPlanServices) {
        return dailyPlanServices.stream().collect(Collectors.groupingBy(DailyPlanService::dailyPlanId));
    }

    private LocalDateTime plusDay(LocalDateTime dateTime, Integer plusDay) {
        return dateTime.plusDays(plusDay);
    }
}
