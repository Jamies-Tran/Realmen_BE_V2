package com.capstone.realmen.service.plans.others.daily.plan.helpers;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dto.plans.daily.account.DailyPlanAccount;
import com.capstone.realmen.data.dto.plans.daily.service.DailyPlanService;

public class DailyPlanHelpers {

    protected List<LocalDateTime> getDailyPlanDateList(LocalDateTime pickupDate) {
        LocalDateTime firstDateOfWeek = getFirstDateOfWeek(pickupDate);
        LocalDateTime lastDateOfWeek = plusDay(firstDateOfWeek, 6);
        List<LocalDateTime> dailyInWeek = new ArrayList<>();
        while(firstDateOfWeek.compareTo(lastDateOfWeek) <= 0) {
            dailyInWeek.add(firstDateOfWeek);
            firstDateOfWeek = plusDay(firstDateOfWeek, 1);
        }
       
        return dailyInWeek.stream().sorted().toList();
    }

    protected List<LocalDateTime> dateListAfterWeek(List<LocalDateTime> createdDate) {
        return createdDate.stream()
                .map(date -> plusDay(date, 7))
                .toList();
    }

    protected LocalDateTime getEquivalentWeeklyDate(LocalDateTime origin, LocalDateTime reference) {
        LocalDateTime endDateOfWeek = getEndDateOfWeek(reference);
        boolean keepForward = true; 
        LocalDateTime pickUpDate = origin;
        while(keepForward) {
            pickUpDate = plusDay(pickUpDate, 7);
            keepForward = plusDay(pickUpDate, 7).compareTo(endDateOfWeek) <= 0;
        }

        return pickUpDate;
    }

    protected Map<Long, List<DailyPlanAccount>> daGroupingBy(List<DailyPlanAccount> dailyPlanAccounts) {
        return dailyPlanAccounts.stream().collect(Collectors.groupingBy(DailyPlanAccount::dailyPlanId));
    }

    protected Map<Long, List<DailyPlanService>> dsGroupingBy(List<DailyPlanService> dailyPlanServices) {
        return dailyPlanServices.stream().collect(Collectors.groupingBy(DailyPlanService::dailyPlanId));
    }

    private LocalDateTime getEndDateOfWeek(LocalDateTime pickUpDate) {

        for(int day = 0; day <= countDayStillSunday(getDayOfWeek(pickUpDate)); day++) {
            pickUpDate = pickUpDate.plusDays(1);
        }
        return pickUpDate;
    }

    private DayOfWeek getDayOfWeek(LocalDateTime time) {
        return time.getDayOfWeek();
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

    private LocalDateTime getFirstDateOfWeek(LocalDateTime dateTime) {
        DayOfWeek dayOfWeek = getDayOfWeek(dateTime);
        switch (dayOfWeek) {
            case MONDAY  -> {return dateTime;}
            case TUESDAY -> {return dateTime.minusDays(1);}
            case WEDNESDAY -> {return dateTime.minusDays(2);}
            case THURSDAY -> {return dateTime.minusDays(3);}
            case FRIDAY -> {return dateTime.minusDays(4);}
            case SATURDAY -> {return dateTime.minusDays(5);}
            case SUNDAY -> {return dateTime.minusDays(6);}
            default -> {throw new NotFoundException("Không tìm thấy ngày trong tuần");}
        }
    }

    private LocalDateTime plusDay(LocalDateTime dateTime, Integer plusDay) {
        return dateTime.plusDays(plusDay);
    }

    // private LocalDate getEndDateOfMonth(LocalDate currentDate) {
    //     return YearMonth.from(currentDate).atEndOfMonth();
    // }

    // private Integer countDayStillEndOfTheMonth(LocalDate pickupDate) {
    //     int count = 0;
    //     while (pickupDate.equals(getEndDateOfMonth(pickupDate))) {
    //         count += 1;
    //         pickupDate.plusDays(1);
    //     }
    //     return count;
    // }

    // private List<LocalDateTime> getDateInMonth(LocalDateTime pickupDate) {
    //     List<LocalDateTime> dailyInWeek = new ArrayList<>();
    //     LocalDate currentDay = pickupDate.toLocalDate();
    //     for (int day = 0; day < countDayStillEndOfTheMonth(currentDay); day++) {
    //         pickupDate = pickupDate.plusDays(1);
    //         dailyInWeek.add(pickupDate);
    //     }
    //     return dailyInWeek.stream().sorted().toList();
    // }
}
