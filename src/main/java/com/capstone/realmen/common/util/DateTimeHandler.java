package com.capstone.realmen.common.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dto.common.DayInWeek;

public class DateTimeHandler {

    public static LocalDate dayNextWeek(LocalDate dateTime) {
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

    public static List<LocalDate> firstAndLastWeek(LocalDate dateTime) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        LocalDate firstDate = dateTime;
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

        LocalDate lastDate = firstDate.plusDays(6);
        return List.of(firstDate, lastDate);
    }

    public static DayInWeek getDayInWeek(LocalDate date) {
        return DayInWeek.of(date.getDayOfWeek());
    }

    public static List<LocalDateTime> validateTimeRange(List<LocalDateTime> timeRange) {
        if (Objects.isNull(timeRange) || timeRange.isEmpty()) {
            return List.of();
        } else if (timeRange.size() == 2) {
            return timeRange.stream()
                    .sorted(Comparator.naturalOrder())
                    .toList();
        } else if (timeRange.size() == 1) {
            return List.of(
                    timeRange.get(0).minusDays(7),
                    timeRange.get(0));
        } else {
            return List.of();
        }

    }
}
