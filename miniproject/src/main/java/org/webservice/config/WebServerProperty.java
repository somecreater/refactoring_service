package org.webservice.config;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@ConfigurationProperties("server")
@Validated
public class WebServerProperty {

    @NotNull
    private int port;
    @NotNull
    private int maxthreads;
    @NotNull
    private int maxconnections;
    @NotNull
    private int minsparethreads;
    @NotNull
    private int connectiontimeout;

    public WebServerProperty(int port, int maxthreads, int maxconnections, int minsparethreads, int connectiontimeout){
        this.port=port;
        this.maxthreads=maxthreads;
        this.maxconnections=maxconnections;
        this.minsparethreads=minsparethreads;
        this.connectiontimeout=connectiontimeout;
    }
}
