package com.capstone.realmen.data.dto.plans.daily;

import java.time.LocalTime;
import java.util.List;

import com.capstone.realmen.common.enums.EShift;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record DailyPlanShiftHour(
        LocalTime from,
        LocalTime to,
        Integer bookingCount,
        String shiftCode,
        String shiftName) {
    public static List<DailyPlanShiftHour> workingShifts(EShift shift) {
        switch (shift) {
            case MORNING_SHIFT:
                return morningShift();
            case NIGHT_SHIFT:
                return nightShift();
            default:
                return List.of();
        }
    }

    private static DailyPlanShiftHour of(LocalTime from, LocalTime to, EShift shift) {
        return DailyPlanShiftHour.builder()
                .from(from)
                .to(to)
                .shiftCode(shift.getCode())
                .shiftName(shift.getName())
                .build();
    }

    private static List<DailyPlanShiftHour> morningShift() {
        return List.of(
                DailyPlanShiftHour.of(LocalTime.parse("07:00:00"), LocalTime.parse("07:30:00"), EShift.MORNING_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("07:30:00"), LocalTime.parse("08:00:00"), EShift.MORNING_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("08:00:00"), LocalTime.parse("08:30:00"), EShift.MORNING_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("08:30:00"), LocalTime.parse("09:00:00"), EShift.MORNING_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("09:00:00"), LocalTime.parse("09:30:00"), EShift.MORNING_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("09:30:00"), LocalTime.parse("10:00:00"), EShift.MORNING_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("10:00:00"), LocalTime.parse("10:30:00"), EShift.MORNING_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("10:30:00"), LocalTime.parse("11:00:00"), EShift.MORNING_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("11:00:00"), LocalTime.parse("11:30:00"), EShift.MORNING_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("11:30:00"), LocalTime.parse("12:00:00"), EShift.MORNING_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("12:00:00"), LocalTime.parse("12:30:00"), EShift.MORNING_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("12:30:00"), LocalTime.parse("13:00:00"), EShift.MORNING_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("13:00:00"), LocalTime.parse("13:30:00"), EShift.MORNING_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("13:30:00"), LocalTime.parse("14:00:00"), EShift.MORNING_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("14:00:00"), LocalTime.parse("14:30:00"), EShift.MORNING_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("14:30:00"), LocalTime.parse("15:00:00"), EShift.MORNING_SHIFT));
    }

    public static List<DailyPlanShiftHour> nightShift() {
        return List.of(
                DailyPlanShiftHour.of(LocalTime.parse("15:00:00"), LocalTime.parse("15:30:00"), EShift.NIGHT_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("15:30:00"), LocalTime.parse("16:00:00"), EShift.NIGHT_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("16:00:00"), LocalTime.parse("16:30:00"), EShift.NIGHT_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("16:30:00"), LocalTime.parse("17:00:00"), EShift.NIGHT_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("17:00:00"), LocalTime.parse("17:30:00"), EShift.NIGHT_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("17:30:00"), LocalTime.parse("18:00:00"), EShift.NIGHT_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("18:00:00"), LocalTime.parse("18:30:00"), EShift.NIGHT_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("18:30:00"), LocalTime.parse("19:00:00"), EShift.NIGHT_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("19:00:00"), LocalTime.parse("19:30:00"), EShift.NIGHT_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("19:30:00"), LocalTime.parse("20:00:00"), EShift.NIGHT_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("20:00:00"), LocalTime.parse("20:30:00"), EShift.NIGHT_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("20:30:00"), LocalTime.parse("21:00:00"), EShift.NIGHT_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("21:00:00"), LocalTime.parse("21:30:00"), EShift.NIGHT_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("21:30:00"), LocalTime.parse("22:00:00"), EShift.NIGHT_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("22:00:00"), LocalTime.parse("22:30:00"), EShift.NIGHT_SHIFT),
                DailyPlanShiftHour.of(LocalTime.parse("22:30:00"), LocalTime.parse("23:00:00"), EShift.NIGHT_SHIFT));
    }
}
