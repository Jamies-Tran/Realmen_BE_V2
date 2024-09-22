package com.capstone.realmen.service.plans.others.daily.plan.data;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.capstone.realmen.common.enums.ERole;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.branch.service.BranchService;

import lombok.Builder;

@Builder
public record DailyPlanCreateRequire(
        Long weeklyPlanId,
        List<Account> accounts,
        List<BranchService> services, 
        LocalDate pickUpDate) {
    public LocalDate pickUpDate() {
        return Objects.requireNonNullElse(pickUpDate, LocalDate.now());
    }

    public List<Long> accountIds() {
        return accounts.stream().map(Account::accountId).toList();
    }

    public List<Long> serviceIds() {
        return services.stream().map(BranchService::shopServiceId).toList();
    }

    public List<Account> accounts() {
        List<String> serviceAssignmentCodes = services.stream()
            .map(BranchService::serviceAssignmentCode)
            .toList();

        return accounts.stream()
            .filter(account -> {
                if(Objects.equals(account.roleCode(), ERole.OPERATOR_STAFF.getCode())) {
                    return serviceAssignmentCodes.contains(account.professionalTypeCode());
                }
                return Objects.equals(account.roleCode(), ERole.RECEPTIONIST.getCode());
            })
            .toList();
    }

    public List<BranchService> services() {
        List<String> professionalTypeCodes = accounts.stream()
            .map(Account::professionalTypeCode)
            .toList();
        
        return services.stream()
            .filter(service -> professionalTypeCodes.contains(service.serviceAssignmentCode()))
            .toList();
    }
}
