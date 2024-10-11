package org.webservice.config;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@ConfigurationProperties("spring.redis")
@Validated
public class RedisProperty {

    @NotEmpty
    private String host;
    @NotNull
    private int port;
    @NotEmpty
    private String password;

    public RedisProperty() {}

    public RedisProperty(String host, int port,String password){
        this.host=host;
        this.port=port;
        this.password=password;
    }
}
