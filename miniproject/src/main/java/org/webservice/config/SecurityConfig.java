package org.webservice.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.webservice.security.Refactoring_Customdetailservice;
import org.webservice.security.customuserdetailservice2;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(SecurityProperty.class)
public class SecurityConfig {

    //이렇게 하면 유지보수가 훨씬 수월해진다. 설정파일 값만 조정하면 된다.
    private final SecurityProperty securityProperty;

    public SecurityConfig(SecurityProperty securityProperty){
        this.securityProperty=securityProperty;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService cusUserDetailsService(){
        return new Refactoring_Customdetailservice();
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler loginsuccesshandler(){
        SavedRequestAwareAuthenticationSuccessHandler handler=new SavedRequestAwareAuthenticationSuccessHandler();
        handler.setDefaultTargetUrl(securityProperty.getUrl().getLoginSuccess());
        return handler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws  Exception{

        httpSecurity.cors(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable);

        httpSecurity.authorizeHttpRequests(
                (auth) -> auth.requestMatchers(
                securityProperty.getUrl().getPermitAll())
                .permitAll().anyRequest().authenticated()
        );

        httpSecurity.formLogin((login)->login
                .usernameParameter(securityProperty.getUsername())
                .passwordParameter(securityProperty.getPassword())
                .loginPage(securityProperty.getUrl().getLoginPage())
                .loginProcessingUrl(securityProperty.getUrl().getLoginAction())
                .failureUrl(securityProperty.getUrl().getLoginFailure())
                .successHandler(loginsuccesshandler())
        );

        httpSecurity.logout((logout)->logout
                .logoutUrl(securityProperty.getUrl().getLogoutAction())
                .invalidateHttpSession(true)
        );

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(cusUserDetailsService())
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

}
