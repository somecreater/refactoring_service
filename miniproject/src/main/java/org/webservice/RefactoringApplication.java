package org.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.webservice.config.*;

@SpringBootApplication(scanBasePackages = {"org.webservice.config"})
@Import({DataBaseConfig.class, SecurityConfig.class, MailConfig.class,
        Oauth2Config.class, RedisConfig.class, SpringConfig.class,
        WebSocketConfig.class})
public class RefactoringApplication {
    public static void main(String[] args){
        SpringApplication.run(RefactoringApplication.class,args);
    }
}
