package com.capstone.realmen.service.plans.others.daily.plan.others.account;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.capstone.realmen.data.dto.plans.daily.account.DailyPlanAccount;
import com.capstone.realmen.data.dto.plans.daily.account.IDailyPlanAccountMapper;
import com.capstone.realmen.repository.database.plans.daily.account.DailyPlanAccountEntity;
import com.capstone.realmen.repository.database.plans.daily.account.IDailyPlanAccountRepository;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.data.DailyPlanAccountCreateRequire;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.data.DailyPlanAccountDeleteRequire;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.data.DailyPlanAccountUpdateRequire;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyPlanAccountCommandService {
        @NonNull
        IDailyPlanAccountRepository dailyPlanAccountRepository;

        @NonNull
        IDailyPlanAccountMapper dailyPlanAccountMapper;

        public void createList(DailyPlanAccountCreateRequire createRequire) {
                if (Objects.nonNull(createRequire.dailyPlanAccounts())) {
                        dailyPlanAccountRepository.saveAll(
                                        createRequire.dailyPlanAccounts()
                                                        .stream()
                                                        .map(dailyPlanAccountMapper::toEntity)
                                                        .toList());
                } else {
                        List<DailyPlanAccount> dailyPlanAccounts = DailyPlanAccount
                                        .of(createRequire.dailyPlanIds(), createRequire.accounts());
                        List<DailyPlanAccountEntity> newDailyPlanAccount = dailyPlanAccounts
                                        .stream()
                                        .map(dailyPlanAccountMapper::toEntity)
                                        .toList();
                        dailyPlanAccountRepository.saveAll(newDailyPlanAccount);
                }

        }

        public List<DailyPlanAccount> update(DailyPlanAccountUpdateRequire updateRequire) {
                DailyPlanAccountDeleteRequire deleteRequire = DailyPlanAccountDeleteRequire
                                .of(updateRequire.dailyPlanId());
                deleteAll(deleteRequire);
                Long dailyPlanId = updateRequire.dailyPlanId();
                List<DailyPlanAccountEntity> dailyPlanAccounts = updateRequire.dailyPlanStaffUpdates()
                                .stream()
                                .map(require -> DailyPlanAccount.builder()
                                                .dailyPlanId(dailyPlanId)
                                                .accountId(require.accountId())
                                                .shiftCode(require.shiftCode())
                                                .shiftName(require.shiftName())
                                                .build())
                                .map(dailyPlanAccountMapper::toEntity)
                                .toList();
                List<DailyPlanAccountEntity> newDailyPlanAccounts = dailyPlanAccountRepository
                                .saveAll(dailyPlanAccounts);
                return newDailyPlanAccounts.stream()
                                .map(dailyPlanAccountMapper::toDto)
                                .toList();
        }

        public void deleteAll(DailyPlanAccountDeleteRequire deleteRequire) {
                dailyPlanAccountRepository.deleteAllByDailyPlanId(deleteRequire.dailyPlanId());
        }
}
