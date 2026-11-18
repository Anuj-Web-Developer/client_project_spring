
package com.billing.login.security;

import org.springframework.security.web.context.NullSecurityContextRepository;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.billing.login.security.jwt.JwtAuthEntryPoint;
import com.billing.login.security.jwt.JwtAuthTokenFilter;
import com.billing.login.security.services.UserDetailsServiceImpl;

@Configuration
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Autowired
    private JwtAuthTokenFilter jwtAuthTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.antMatcher("/api/**")
            .cors().and()
            .csrf().disable()
            .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
            .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .securityContext()
                .securityContextRepository(new NullSecurityContextRepository())
            .and()
            .authorizeRequests()
            .antMatchers(
                    "/api/auth/signin",
                    "/api/auth/signup",
                    "/api/auth/forgotPassword",
                    "/api/auth/changePassword/**",
                    "/otp/sendOtp/**",
                    "/otp/verifyOtp/**",
                    "/api/auth/sendUserOTP/**",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
            ).permitAll()
            .antMatchers("/api/auth/updateUser").hasRole("ADMIN")
            .antMatchers("/api/auth/addProfile").hasRole("ADMIN")
            .antMatchers("/api/auth/userList").hasRole("ADMIN")
            .antMatchers("/api/customer/getAllCustomers").hasRole("ADMIN")
//            .antMatchers("/api/user/**").hasRole("USER")
            .anyRequest().authenticated()

            .and()
            .exceptionHandling()
            .accessDeniedHandler((request, response, ex) -> {

                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("text/plain");

                response.getWriter().write(
                        "You do not have permission to access this resource."
                );
            });

        http.addFilterBefore(jwtAuthTokenFilter,
                UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}