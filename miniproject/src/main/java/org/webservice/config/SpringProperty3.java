package org.webservice.config;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.unit.DataSize;
import org.springframework.validation.annotation.Validated;

@Getter
@ConfigurationProperties("spring.servlet.multipart")
@Validated
public class SpringProperty3 {

    @NotNull
    private boolean enabled;
    @NotNull
    private DataSize maxFileSize;
    @NotNull
    private DataSize maxRequestSize;

    public SpringProperty3(boolean enabled, DataSize maxFileSize, DataSize maxRequestSize){
        this.enabled=enabled;
        this.maxFileSize=maxFileSize;
        this.maxRequestSize=maxRequestSize;
    }
}
