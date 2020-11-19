package com.archivsoft.nobelWill.config;

import com.archivsoft.nobelWill.config.handler.CustomAccessDeniedHandler;
import com.archivsoft.nobelWill.config.handler.CustomAuthenticationSuccessHandler;
import com.archivsoft.nobelWill.config.handler.CustomLoginFailureHandler;
import com.archivsoft.nobelWill.config.handler.CustomLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.sql.DataSource;

@Configuration
public class BeanConfig {

    @Bean
    public PasswordEncoder  passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler(){return new CustomAuthenticationSuccessHandler("/hello");}

    @Bean
    public AuthenticationFailureHandler loginFailureHandler(){return new CustomLoginFailureHandler();}

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){return new CustomLogoutSuccessHandler();}

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

}
