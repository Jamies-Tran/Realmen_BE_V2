package com.capstone.realmen.controller.security.user;

import java.util.Objects;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.enums.EAccountStatus;
import com.capstone.realmen.common.enums.ERole;
import com.capstone.realmen.controller.handler.exceptions.LoginException;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.service.account.data.AccountSearchByField;
import com.capstone.realmen.service.account.usecase.admin.IAdminAccountService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomUserDetailService implements UserDetailsService {
    @NonNull
    IAdminAccountService accountAdminService;

    @Override
    public UserDetails loadUserByUsername(String phoneOrStaffCode) throws UsernameNotFoundException {
        Account account = accountAdminService.adminFindByPhoneOrStaffCode(AccountSearchByField.of(phoneOrStaffCode));
        if(!Objects.equals(account.accountStatusCode(), EAccountStatus.ACTIVE.getCode())) {
            throw new LoginException("Tài khoản không hợp lệ");
        }
        ERole role = ERole.findByCode(account.roleCode())
                .orElseThrow(NotFoundException::new);
        return User.builder().username(phoneOrStaffCode).password(account.password())
                .authorities(role.authority()).build();
    }
}
