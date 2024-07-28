package com.capstone.realmen.service.plans.others.daily.plan.others.account;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.realmen.data.dto.plans.daily.account.DailyPlanAccount;
import com.capstone.realmen.data.dto.plans.daily.account.IDailyPlanAccountMapper;
import com.capstone.realmen.repository.database.account.plans.IDailyPlanAccountRepository;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.data.DailyPlanAccountSearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyPlanAccountQueryService {
    @NonNull
    IDailyPlanAccountRepository dailyPlanAccountRepository;

    @NonNull
    IDailyPlanAccountMapper dailyPlanAccountMapper;

    public List<DailyPlanAccount> findAllBy(DailyPlanAccountSearchByField searchByField) {
        return dailyPlanAccountRepository.findAllByDailyPlanId(searchByField.dailyPlanId())
                .stream()
                .map(dailyPlanAccountMapper::toDto)
                .toList();
    }
}