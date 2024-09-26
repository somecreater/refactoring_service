package org.webservice.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@ConfigurationProperties("spring.datasource")
@Validated
public class DataBaseProperty{

    //그냥 properties 파일에 넣으면 자동 설정 되지만 값에 대한 검증을 위해 이렇게 구현한다.
    @NotEmpty
    private String driverclassname;
    @NotEmpty
    private String uri;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private hikaricp etc;


    public DataBaseProperty(String driverclassname, String uri, String username, String password, hikaricp etc){
        this.driverclassname=driverclassname;
        this.uri=uri;
        this.username=username;
        this.password=password;
        this.etc=etc;
    }

    @Getter
    public static class hikaricp{

        @Min(10)
        @Max(300)
        private int max_pool_size;
        @Min(10000)
        @Max(60000)
        private int connection_timeout;
        @Min(300000)
        @Max(900000)
        private int idle_timeout;
        @Min(600000)
        @Max(1800000)
        private int max_lifetime;

        public hikaricp(int max_pool_size, int connection_timeout, int idle_timeout, int max_lifetime){
            this.max_pool_size=max_pool_size;
            this.connection_timeout=connection_timeout;
            this.idle_timeout=idle_timeout;
            this.max_lifetime=max_lifetime;
        }
    }

}
