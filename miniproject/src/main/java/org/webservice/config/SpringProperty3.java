package org.webservice.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
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
    @Min(2621440)
    @Max(52428800)
    private DataSize maxFileSize;
    @Min(10485760)
    @Max(104857600)
    private DataSize maxRequestSize;

    public SpringProperty3(boolean enabled, DataSize maxFileSize, DataSize maxRequestSize){
        this.enabled=enabled;
        this.maxFileSize=maxFileSize;
        this.maxRequestSize=maxRequestSize;
    }
}
