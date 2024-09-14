package com.capstone.realmen.service.plans.others.daily.plan.helpers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.capstone.realmen.common.enums.EDailyPlanStatus;
import com.capstone.realmen.common.enums.EProfessional;
import com.capstone.realmen.common.enums.EServiceAssignment;
import com.capstone.realmen.common.util.DateTimeHandler;
import com.capstone.realmen.controller.handler.exceptions.InvalidRequest;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.branch.service.BranchService;
import com.capstone.realmen.data.dto.plans.daily.account.DailyPlanAccount;
import com.capstone.realmen.data.dto.plans.daily.service.DailyPlanService;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanServiceUpdate;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanStaffUpdate;

public class DailyPlanHelpers {
    public void verifyUpdate(List<DailyPlanStaffUpdate> staffs, List<DailyPlanServiceUpdate> services) {
        List<String> assignmentTypeCodes = services.stream()
                .map(DailyPlanServiceUpdate::assignmentTypeCode)
                .toList();

        List<String> serviceAssignmentCodes = staffs.stream()
            .map(DailyPlanStaffUpdate::serviceAssignmentCode)
            .toList();

        for (DailyPlanStaffUpdate staff : staffs) {
            if(!assignmentTypeCodes.contains(staff.serviceAssignmentCode())) {
                EServiceAssignment serviceAssignment = EServiceAssignment.findByCode(staff.serviceAssignmentCode());
                throw new InvalidRequest(String.format("Thiếu %s", serviceAssignment.getName().toLowerCase()));
            }
        }

        for (DailyPlanServiceUpdate service : services) {
            if(!serviceAssignmentCodes.contains(service.assignmentTypeCode())) {
                EProfessional professional = EProfessional.findByCode(service.assignmentTypeCode());
                throw new InvalidRequest(String.format("Thiếu %s", professional.getName().toLowerCase()));
            }
        }
    }

    public EDailyPlanStatus getDailyPlanStatus(List<Account> staffs, List<BranchService> services) {
        if((Objects.isNull(staffs) || staffs.isEmpty()) || (Objects.isNull(services) || services.isEmpty())) {
            return EDailyPlanStatus.DISABLED;
        }

        return EDailyPlanStatus.DRAFT;
    }

    protected List<LocalDate> getDailyPlanDateList(LocalDate pickupDate) {
        List<LocalDate> firstAndLast = DateTimeHandler.firstAndLastWeek(pickupDate);
        LocalDate firstDate = firstAndLast.get(0);
        LocalDate lastDate = firstAndLast.get(1);
        List<LocalDate> dailyInWeek = new ArrayList<>();
        while (firstDate.compareTo(lastDate) <= 0) {
            dailyInWeek.add(firstDate);
            firstDate = plusDay(firstDate, 1);
        }

        return dailyInWeek.stream().sorted().toList();
    }

    protected List<LocalDate> dateListAfterWeek(List<LocalDate> createdDate) {
        return createdDate.stream()
                .map(date -> DateTimeHandler.dayNextWeek(date))
                .toList();
    }

    protected LocalDate getEquivalentWeeklyDate(LocalDate origin, LocalDate reference) {
        List<LocalDate> firstAndLast = DateTimeHandler.firstAndLastWeek(reference);
        LocalDate lastDate = firstAndLast.get(1);
        boolean keepForward = true;
        LocalDate pickUpDate = origin;
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

    private LocalDate plusDay(LocalDate dateTime, Integer plusDay) {
        return dateTime.plusDays(plusDay);
    }
}
