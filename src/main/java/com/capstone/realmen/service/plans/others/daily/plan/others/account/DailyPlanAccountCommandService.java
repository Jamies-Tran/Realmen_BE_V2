package com.capstone.realmen.service.plans.others.daily.plan.others.account;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.capstone.realmen.data.dto.plans.daily.account.DailyPlanAccount;
import com.capstone.realmen.data.dto.plans.daily.account.IDailyPlanAccountMapper;
import com.capstone.realmen.repository.database.account.plans.DailyPlanAccountEntity;
import com.capstone.realmen.repository.database.account.plans.IDailyPlanAccountRepository;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.data.DailyPlanAccountCreateRequire;

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
            List<DailyPlanAccountEntity> newDailyPlanAccount = DailyPlanAccount
                    .of(createRequire.dailyPlanIds(), createRequire.accountIds())
                    .stream()
                    .map(dailyPlanAccountMapper::toEntity)
                    .toList();
            dailyPlanAccountRepository.saveAll(newDailyPlanAccount);
        }

    }
}
