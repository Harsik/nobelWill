package com.archivsoft.nobelWill.config;

import com.archivsoft.nobelWill.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationSuccessHandler loginSuccessHandler;
    private AuthenticationFailureHandler loginFailureHandler;
    private LogoutSuccessHandler logoutSuccessHandler;
    private AccessDeniedHandler accessDeniedHandler;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .headers().frameOptions().disable().and()
                .csrf().disable()
                .authorizeRequests()
                /*페이지 권한 설정*/
                .antMatchers("/console/**").permitAll()
//                .antMatchers("/").access("hasRole('SUPER') or hasRole('MANAGER') or hasRole('NORMAL')")
                .antMatchers("/**").permitAll()
                .and()

                /* 로그인 설정*/
                .formLogin()
                .loginPage("/signin")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
//                .failureForwardUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()

                /*로그아웃 설정*/
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()

                /*리멤버미 설정*/
                .rememberMe()
                .tokenValiditySeconds(60 * 30) // 30 minutes
                .and()

                /*403 예외처리 핸들링*/
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
//              .accessDeniedPage("/denied")
                .and()

        /*유저 최대 세션*/
//                .sessionManagement()
//                .maximumSessions(1)
//                .expiredUrl("/login")
        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}
