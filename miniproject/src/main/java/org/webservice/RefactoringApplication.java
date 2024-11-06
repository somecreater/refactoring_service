package org.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication(scanBasePackages = {
        "org.webservice.schedule",
        "org.webservice.config",
        "org.webservice.refactoring_service",
        "org.webservice.security",
        "org.webservice.refactoring_task",
        "org.webservice.refactoring_controller"
})
@EntityScan(basePackages = "org.webservice.entity")
public class RefactoringApplication {
    public static void main(String[] args){
        SpringApplication.run(RefactoringApplication.class,args);
    }
}
