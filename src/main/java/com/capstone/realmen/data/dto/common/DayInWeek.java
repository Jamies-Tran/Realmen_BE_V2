package com.capstone.realmen.data.dto.common;

import java.time.DayOfWeek;
import lombok.Builder;

@Builder
public record DayInWeek(
                String dayInWeekCode,
                String dayInWeekName) {

        public static DayInWeek of(DayOfWeek dayOfWeek) {
                switch (dayOfWeek) {
                        case MONDAY:

                                return DayInWeek.builder()
                                                .dayInWeekCode(dayOfWeek.name())
                                                .dayInWeekName("Thứ 2")
                                                .build();
                        case TUESDAY:

                                return DayInWeek.builder()
                                                .dayInWeekCode(dayOfWeek.name())
                                                .dayInWeekName("Thứ 3")
                                                .build();

                        case WEDNESDAY:

                                return DayInWeek.builder()
                                                .dayInWeekCode(dayOfWeek.name())
                                                .dayInWeekName("Thứ tư")
                                                .build();

                        case THURSDAY:

                                return DayInWeek.builder()
                                                .dayInWeekCode(dayOfWeek.name())
                                                .dayInWeekName("Thứ 5")
                                                .build();
                        case FRIDAY:

                                return DayInWeek.builder()
                                                .dayInWeekCode(dayOfWeek.name())
                                                .dayInWeekName("Thứ 6")
                                                .build();

                        case SATURDAY:

                                return DayInWeek.builder()
                                                .dayInWeekCode(dayOfWeek.name())
                                                .dayInWeekName("Thứ 7")
                                                .build();

                        case SUNDAY:

                                return DayInWeek.builder()
                                                .dayInWeekCode(dayOfWeek.name())
                                                .dayInWeekName("Chủ nhật")
                                                .build();
                        default:
                                return null;
                }
        }
}
