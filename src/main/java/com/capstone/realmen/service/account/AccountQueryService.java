package com.capstone.realmen.service.account;

import org.springframework.stereotype.Service;

import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.account.IAccountMapper;
import com.capstone.realmen.repository.database.account.AccountRepository;
import com.capstone.realmen.service.account.data.SearchByField;

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

    public Account find(SearchByField searchByField) {
        return accountMapper.toDto(
            accountRepository.findByPhoneOrStaffCode(searchByField.search())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy tài khoản"))
        );
    } 
}
