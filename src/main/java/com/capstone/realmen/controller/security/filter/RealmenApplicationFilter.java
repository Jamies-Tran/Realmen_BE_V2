package com.capstone.realmen.controller.security.filter;

import java.io.IOException;
import java.util.Objects;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.capstone.realmen.controller.security.token.AccessTokenService;
import com.capstone.realmen.controller.security.user.CustomUserDetailService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RealmenApplicationFilter extends OncePerRequestFilter {

    @NonNull
    CustomUserDetailService customUserDetail;
    @NonNull
    AccessTokenService accessTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String getIdentifier = accessTokenService.getIdentifyFromHeader(request);
        if (Objects.nonNull(getIdentifier)) {
            UserDetails userDetails = customUserDetail.loadUserByUsername(getIdentifier);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(token);
            SecurityContextHolder.setContext(securityContext);
        }
        filterChain.doFilter(request, response);
    }

}
