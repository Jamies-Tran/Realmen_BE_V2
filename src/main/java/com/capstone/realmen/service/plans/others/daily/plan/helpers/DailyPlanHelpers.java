package com.capstone.realmen.service.plans.others.daily.plan.helpers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import com.capstone.realmen.common.enums.EDailyPlanCreateType;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;

public class DailyPlanHelpers {

    protected List<LocalDateTime> getDailyPlanDateList(LocalDateTime pickupDate, EDailyPlanCreateType createType) {
        switch (createType) {
            case WEEKLY:
                return getDateInWeek(pickupDate);
            case MONTHLY:
                return getDateInMonth(pickupDate);
            default:
                return List.of();
        }
    }

    private List<LocalDateTime> getDateInWeek(LocalDateTime pickupDate) {
        List<LocalDateTime> dailyInWeek = new ArrayList<>();
        DayOfWeek currentDay = getDayOfWeek(pickupDate);
        for (int day = 0; day < countDayStillSunday(currentDay); day++) {
            pickupDate = pickupDate.plusDays(day);
            dailyInWeek.add(pickupDate);
        }
        return dailyInWeek.stream().sorted().toList();
    }

    private List<LocalDateTime> getDateInMonth(LocalDateTime pickupDate) {
        List<LocalDateTime> dailyInWeek = new ArrayList<>();
        LocalDate currentDay = pickupDate.toLocalDate();
        for (int day = 0; day < countDayStillEndOfTheMonth(currentDay); day++) {
            pickupDate = pickupDate.plusDays(day);
            dailyInWeek.add(pickupDate);
        }
        return dailyInWeek.stream().sorted().toList();
    }

    private DayOfWeek getDayOfWeek(LocalDateTime time) {
        return time.getDayOfWeek();
    }

    private LocalDate getEndDateOfMonth(LocalDate currentDate) {
        return YearMonth.from(currentDate).atEndOfMonth();
    }

    private Integer countDayStillEndOfTheMonth(LocalDate pickupDate) {
        int count = 0;
        while (pickupDate.equals(getEndDateOfMonth(pickupDate))) {
            count += 1;
            pickupDate.plusDays(1);
        }
        return count;
    }

    private Integer countDayStillSunday(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY:
                return 6;
            case TUESDAY:
                return 5;
            case WEDNESDAY:
                return 4;
            case THURSDAY:
                return 3;
            case FRIDAY:
                return 2;
            case SATURDAY:
                return 1;
            case SUNDAY:
                return 0;
            default:
                throw new NotFoundException("Không tìm thấy ngày trong tuần");
        }
    }
}
