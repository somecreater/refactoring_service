package org.webservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity
public class oauth2config extends WebSecurityConfigurerAdapter{
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .oauth2Login(); // 이 부분이 <security:oauth2-login>에 해당합니다.
    }
}
