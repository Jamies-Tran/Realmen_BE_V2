package com.capstone.realmen.common.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.capstone.realmen.controller.handler.exceptions.NotFoundException;

public class DateTimeHandler {
    
    public static List<LocalDateTime> validateTimeRange(List<LocalDateTime> timeRange) {
        if(Objects.nonNull(timeRange) && timeRange.size() == 1) {
            timeRange.add(timeRange.get(0).plusDays(7));
        }
        return timeRange;
    }

    public static LocalDateTime dayNextWeek(LocalDateTime dateTime) {
        return dateTime.plusDays(7);
    }

    public static Integer remainDayOfWeek(DayOfWeek dayOfWeek) {
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

    public static List<LocalDateTime> firstAndLast(LocalDateTime dateTime) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        LocalDateTime firstDate = dateTime;
        switch (dayOfWeek) {
            case MONDAY -> {
                break;
            }
            case TUESDAY -> {
                firstDate = dateTime.minusDays(1);
                break;
            }
            case WEDNESDAY -> {
                firstDate = dateTime.minusDays(2);
                break;
            }
            case THURSDAY -> {
                firstDate = dateTime.minusDays(3);
                break;
            }
            case FRIDAY -> {
                firstDate = dateTime.minusDays(4);
                break;

            }
            case SATURDAY -> {
                firstDate = dateTime.minusDays(5);
                break;
            }
            case SUNDAY -> {
                firstDate = dateTime.minusDays(6);
                break;
            }
            default -> {
                throw new NotFoundException("Không tìm thấy ngày trong tuần");
            }
        }

        LocalDateTime lastDate = firstDate.plusDays(6);
        return List.of(firstDate, lastDate);
    }
}
