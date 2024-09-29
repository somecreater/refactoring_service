package org.webservice.config;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@ConfigurationProperties("spring.security")
@Validated
public class SecurityProperty {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotNull
    private url url;

    public SecurityProperty(String username, String password, url url){
        this.username=username;
        this.password=password;
        this.url=url;
    }

    @Getter
    public static class url{
        @NotEmpty
        private String loginPage;
        @NotEmpty
        private String loginAction;
        @NotEmpty
        private String loginSuccess;
        @NotEmpty
        private String loginFailure;
        @NotEmpty
        private String logoutAction;
        @NotEmpty
        private String[] permitAll;
        public url(String loginPage,String loginAction, String loginSuccess,
                                String loginFailure, String logoutAction,String[] permitAll){
            this.loginPage=loginPage;
            this.loginAction=loginAction;
            this.loginSuccess=loginSuccess;
            this.loginFailure=loginFailure;
            this.logoutAction=logoutAction;
            this.permitAll=permitAll;
        }
    }
}
