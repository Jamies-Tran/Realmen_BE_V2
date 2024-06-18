package com.capstone.realmen.service.account;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.account.IAccountMapper;
import com.capstone.realmen.repository.database.account.AccountRepository;
import com.capstone.realmen.service.account.data.AccountSearchByField;
import com.capstone.realmen.service.account.data.AccountSearchCriteria;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountQueryService {
    @NonNull
    AccountRepository accountRepository;
    @NonNull
    IAccountMapper accountMapper;

    public Account find(AccountSearchByField searchByField) {
        return accountMapper.toDto(
                accountRepository.findByPhoneOrStaffCode(searchByField.search())
                        .orElseThrow(() -> new NotFoundException("Không tìm thấy tài khoản")));
    }

    public Page<Account> findAll(AccountSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return accountRepository
                .findAll(
                        searchCriteria.toLowerCase(),
                        searchCriteria.defaultStatusCodes(),
                        pageRequestCustom.pageRequest())
                .map(accountMapper::toDto);
    }
}
