package org.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.webservice.config.DataBaseConfig;
import org.webservice.config.SecurityConfig;

@SpringBootApplication(scanBasePackages = {"org.webservice.config","org.webservice.service_1"})
@Import({DataBaseConfig.class, SecurityConfig.class})
public class RefactoringApplication {
    public static void main(String[] args){
        SpringApplication.run(RefactoringApplication.class,args);
    }
}
