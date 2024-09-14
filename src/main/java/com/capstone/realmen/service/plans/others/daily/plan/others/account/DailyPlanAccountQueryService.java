package com.capstone.realmen.service.plans.others.daily.plan.others.account;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.enums.EShift;
import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dao.plans.daily.account.DailyPlanAccountDAO;
import com.capstone.realmen.data.dto.plans.daily.DailyPlanShiftHour;
import com.capstone.realmen.data.dto.plans.daily.account.DailyPlanAccount;
import com.capstone.realmen.data.dto.plans.daily.account.IDailyPlanAccountMapper;
import com.capstone.realmen.repository.database.plans.daily.account.IDailyPlanAccountRepository;
import com.capstone.realmen.service.booking.other.BookingServiceQueryService;
import com.capstone.realmen.service.booking.other.data.BookingServiceCountRequire;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.data.DailyPlanAccountSearchByField;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.data.DailyPlanAccountSearchCriteria;

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

    @NonNull
    BookingServiceQueryService bsQuery;
    

    public List<DailyPlanAccount> findAllBy(DailyPlanAccountSearchByField searchByField) {
        if(Objects.nonNull(searchByField.dailyPlanId())) {
            return dailyPlanAccountRepository.findAllByDailyPlanId(searchByField.dailyPlanId())
                .stream()
                .map(dailyPlanAccountMapper::toDto)
                .toList();
        } 
        if(Objects.nonNull(searchByField.dailyPlanIds())) {
            return dailyPlanAccountRepository.findAllByDailyPlanIdIn(searchByField.dailyPlanIds())
                .stream()
                .map(dailyPlanAccountMapper::toDto)
                .toList();
        }
        return List.of();
    }

    public DailyPlanAccount findById(DailyPlanAccountSearchByField searchByField) {
        DailyPlanAccountDAO foundDailyPlanAccount = dailyPlanAccountRepository.findByDailyPlanAccountId(searchByField.dailyPlanAccountId())
            .orElseThrow(NotFoundException::new);
        
        List<DailyPlanShiftHour> shifts = DailyPlanShiftHour.workingShifts(EShift.findByCode(foundDailyPlanAccount.getShiftCode()));
        BookingServiceCountRequire countRequire = BookingServiceCountRequire.of(foundDailyPlanAccount.getAccountId(), foundDailyPlanAccount.getDate().toLocalDate(), shifts);
        List<DailyPlanShiftHour> countBookingInShift = bsQuery.countBookingInShift(countRequire);

        return dailyPlanAccountMapper.toDto(foundDailyPlanAccount)
            .withWorkingSlots(countBookingInShift);
    }

    public Page<DailyPlanAccount> findAll(DailyPlanAccountSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return dailyPlanAccountRepository.findAll(searchCriteria, pageRequestCustom.pageRequest())
            .map(dailyPlanAccountMapper::toDto);
    }
}
