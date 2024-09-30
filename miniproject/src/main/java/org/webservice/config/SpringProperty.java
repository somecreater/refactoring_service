package org.webservice.config;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@ConfigurationProperties("spring")
@Validated
public class SpringProperty {

    @NotNull
    private application application;
    @NotNull
    private resources resources;

    public SpringProperty(application application, resources resources){
        this.application=application;
        this.resources=resources;
    }
    @Getter
    public static class application{
        @NotEmpty
        private String name;
        public application(String name){
            this.name=name;
        }
    }
    @Getter
    public static class resources{
        @NotEmpty
        private String staticLocations;
        public resources(String staticLocations){
            this.staticLocations=staticLocations;
        }
    }

}
