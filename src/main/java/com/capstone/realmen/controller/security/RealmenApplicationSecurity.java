package com.capstone.realmen.controller.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.capstone.realmen.controller.security.encoder.AppPasswordEncoder;
import com.capstone.realmen.controller.security.filter.RealmenApplicationFilter;
import com.capstone.realmen.controller.security.user.CustomUserDetailService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RealmenApplicationSecurity {
    @NonNull
    AppPasswordEncoder appPasswordEncoder;

    @NonNull
    CustomUserDetailService customUserDetailService;

    @NonNull
    RealmenApplicationFilter appFilter;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(appPasswordEncoder.passwordEncoder());
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(daoAuthenticationProvider).build();
    }

    private CorsConfigurationSource configurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/web/**", corsConfiguration);
        source.registerCorsConfiguration("/mobile/**", corsConfiguration);
        source.registerCorsConfiguration("/collection/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors(t -> t.configurationSource(configurationSource())).csrf(t -> t.disable())
                .sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(appFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(
                        t -> t.requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui/index.html"),
                                AntPathRequestMatcher.antMatcher("/swagger-ui/*"),
                                AntPathRequestMatcher.antMatcher("/v3/api-docs/*"),
                                AntPathRequestMatcher.antMatcher("/v3/api-docs"),
                                AntPathRequestMatcher.antMatcher("/web/auth"),
                                AntPathRequestMatcher.antMatcher("/web/accounts/otp/change-password"),
                                AntPathRequestMatcher.antMatcher("/web/accounts/change-password"),
                                AntPathRequestMatcher.antMatcher("/mobile/accounts"),
                                AntPathRequestMatcher.antMatcher("/mobile/auth/**"),
                                AntPathRequestMatcher.antMatcher("/mobile/auth/create-otp"),
                                AntPathRequestMatcher.antMatcher("/collection/**")).permitAll())

                .authorizeHttpRequests(t -> t.anyRequest().authenticated())

                .build();
    }
    
}
