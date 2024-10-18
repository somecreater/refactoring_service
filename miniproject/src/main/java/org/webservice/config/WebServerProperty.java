package org.webservice.config;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@ConfigurationProperties("server")
@Validated
public class WebServerProperty {

    @NotEmpty
    private int port;
    @NotEmpty
    private int maxthreads;
    @NotEmpty
    private int maxconnections;
    @NotEmpty
    private int minsparethreads;
    @NotEmpty
    private int connectiontimeout;

    public WebServerProperty(int port, int maxthreads, int maxconnections, int minsparethreads, int connectiontimeout){
        this.port=port;
        this.maxthreads=maxthreads;
        this.maxconnections=maxconnections;
        this.minsparethreads=minsparethreads;
        this.connectiontimeout=connectiontimeout;
    }
}
