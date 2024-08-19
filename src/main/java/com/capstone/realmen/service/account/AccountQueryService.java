package com.capstone.realmen.service.account;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dao.account.AccountDAO;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.account.IAccountMapper;
import com.capstone.realmen.repository.database.account.AccountEntity;
import com.capstone.realmen.repository.database.account.IAccountRepository;
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
        IAccountRepository accountRepository;
        @NonNull
        IAccountMapper accountMapper;

        public Account find(AccountSearchByField searchByField) {
                if (Objects.nonNull(searchByField.search())) {
                        AccountDAO dFoundAccount = accountRepository
                                        .findByPhoneOrStaffCode(searchByField.search())
                                        .orElseThrow(() -> new NotFoundException("Không tìm thấy tài khoản"));
                        return accountMapper.toDto(dFoundAccount);
                }
                if (Objects.nonNull(searchByField.accountId())) {
                        AccountEntity eFoundAccount = accountRepository
                                        .findById(searchByField.accountId())
                                        .orElseThrow(() -> new NotFoundException("Không tìm thấy tài khoản"));
                        return accountMapper.toDto(eFoundAccount);
                }

                throw new NotFoundException("Thông tin tìm kiếm không hợp lệ");

        }

        public Page<Account> findAll(AccountSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
                return accountRepository
                                .findAll(
                                                searchCriteria.toLowerCase(),
                                                searchCriteria.defaultStatusCodes(),
                                                pageRequestCustom.pageRequest())
                                .map(accountMapper::toDto);
        }

        public List<Account> findAllByIds(AccountSearchByField searchByField) {
                return accountRepository.findAllByIds(searchByField.accountIds())
                                .stream()
                                .map(accountMapper::toDto)
                                .toList();
        }
}
