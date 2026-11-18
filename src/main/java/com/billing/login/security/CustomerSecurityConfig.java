package com.billing.login.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@Order(2)
public class CustomerSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.antMatcher("/auth/customer/**")
            .cors().and()
            .csrf().disable()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
            .authorizeRequests()
            .antMatchers(
                    "/auth/customer/send-otp/**",
                    "/auth/customer/verify-otp/**",
                    "/auth/customer/logout",
                    "/auth/customer/check-session"
            ).permitAll()
            .anyRequest().authenticated();
    }
}