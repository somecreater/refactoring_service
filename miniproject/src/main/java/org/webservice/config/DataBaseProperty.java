package org.webservice.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@ConfigurationProperties("spring.datasource")
@Validated
public class DataBaseProperty{

    //그냥 properties 파일에 넣으면 자동 설정 되지만 값에 대한 검증을 위해 이렇게 구현한다.
    @NotEmpty
    private String driverClassName;
    @NotEmpty
    private String url;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotNull
    private Hikari hikari;


    public DataBaseProperty(String driverclassname, String url, String username, String password, Hikari hikari){
        this.driverClassName=driverclassname;
        this.url=url;
        this.username=username;
        this.password=password;
        this.hikari=hikari;
    }

    @Getter
    public static class Hikari {

        @Min(10)
        @Max(300)
        private int maxPoolSize;
        @Min(10000)
        @Max(60000)
        private int connectionTimeout;
        @Min(300000)
        @Max(900000)
        private int idleTimeout;
        @Min(600000)
        @Max(1800000)
        private int maxLifetime;

        public Hikari(int max_pool_size, int connection_timeout, int idle_timeout, int max_lifetime){
            this.maxPoolSize=max_pool_size;
            this.connectionTimeout=connection_timeout;
            this.idleTimeout=idle_timeout;
            this.maxLifetime=max_lifetime;
        }
    }

}
