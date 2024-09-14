package com.capstone.realmen.data.dto.plans.daily.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import com.capstone.realmen.common.enums.ERole;
import com.capstone.realmen.common.enums.EShift;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.plans.daily.DailyPlanShiftHour;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record DailyPlanAccount(
                Long dailyPlanAccountId,
                Long dailyPlanId,
                Long accountId,
                String fullName,
                String phone,
                String genderCode,
                String genderName,
                String professionalTypeCode,
                String professionalTypeName,
                String thumbnail,
                String accountStatusCode,
                String accountStatusName,
                String shiftCode,
                String shiftName,
                List<DailyPlanShiftHour> workingSlots) {
        public static List<DailyPlanAccount> of(List<Long> accountIds) {
                return accountIds.stream()
                                .map(accountId -> DailyPlanAccount.builder()
                                                .accountId(accountId).build())
                                .toList();
        }

        public static List<DailyPlanAccount> of(List<Long> dailyPlanIds, List<Account> accounts) {
                List<DailyPlanAccount> dailyPlanAccounts = new ArrayList<>();
                for (Long dailyPlanId : dailyPlanIds) {
                        List<Long> staffIds = accounts.stream()
                                        .filter(account -> Objects.equals(account.roleCode(),
                                                        ERole.OPERATOR_STAFF.getCode()))
                                        .map(Account::accountId)
                                        .toList();
                        List<Long> receptIds = accounts.stream()
                                        .filter(account -> Objects.equals(account.roleCode(),
                                                        ERole.RECPETIONIST.getCode()))
                                        .map(Account::accountId)
                                        .toList();

                        List<DailyPlanAccount> assignShiftForStaffs = assignShift(staffIds, dailyPlanId);
                        List<DailyPlanAccount> assignShiftForRecepts = assignShift(receptIds, dailyPlanId);
                        Stream.concat(assignShiftForStaffs.stream(), assignShiftForRecepts.stream())
                                        .forEach(dailyPlanAccounts::add);
                }
                return dailyPlanAccounts;
        }

        private static List<DailyPlanAccount> assignShift(List<Long> accountIds, Long dailyPlanId) {
                List<DailyPlanAccount> dailyPlanAccounts = new ArrayList<>();
                if (accountIds.size() == 1) {
                        DailyPlanAccount dailyPlanAccount = DailyPlanAccount.builder()
                                        .accountId(accountIds.get(0))
                                        .dailyPlanId(dailyPlanId)
                                        .shiftCode(EShift.MORNING_SHIFT.getCode())
                                        .shiftName(EShift.MORNING_SHIFT.getName())
                                        .build();
                        dailyPlanAccounts.add(dailyPlanAccount);
                } else {
                        int startIndex = 0;
                        long endIndexInDouble = Math.round(((double) accountIds.size()) / 2);
                        int endIndex = Long.valueOf(endIndexInDouble).intValue();
                        List<DailyPlanAccount> nShift = accountIds.subList(startIndex, endIndex)
                                        .stream()
                                        .map(accountId -> DailyPlanAccount.builder()
                                                        .accountId(accountId)
                                                        .dailyPlanId(dailyPlanId)
                                                        .shiftCode(EShift.NIGHT_SHIFT.getCode())
                                                        .shiftName(EShift.NIGHT_SHIFT.getName())
                                                        .build())
                                        .toList();
                        List<DailyPlanAccount> dShift = accountIds
                                        .stream()
                                        .filter(accountId -> !nShift.stream()
                                                        .map(a -> a.accountId)
                                                        .toList()
                                                        .contains(accountId))
                                        .map(accountId -> DailyPlanAccount.builder()
                                                        .accountId(accountId)
                                                        .dailyPlanId(dailyPlanId)
                                                        .shiftCode(EShift.MORNING_SHIFT.getCode())
                                                        .shiftName(EShift.MORNING_SHIFT.getName())
                                                        .build())
                                        .toList();
                        Stream.concat(dShift.stream(), nShift.stream()).forEach(dailyPlanAccounts::add);
                }
                return dailyPlanAccounts;
        }

        public static List<DailyPlanAccount> duplicate(List<DailyPlanAccount> dailyPlanAccounts, Long newDailyPlanId) {
                return dailyPlanAccounts.stream()
                                .map(dailyPlanAcc -> DailyPlanAccount.builder()
                                                .accountId(dailyPlanAcc.accountId())
                                                .dailyPlanId(newDailyPlanId)
                                                .build())
                                .toList();
        }
}
