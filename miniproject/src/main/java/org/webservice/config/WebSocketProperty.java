package org.webservice.config;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@ConfigurationProperties("websocket")
@Validated
public class WebSocketProperty {

    @NotEmpty
    private String allowedOrigins;
    @NotEmpty
    private String mappingPath;
    @NotEmpty
    private String stompEndpoint;
    @NotEmpty
    private String brokerPrefix;
    @NotEmpty
    private String applicationDestinationPrefix;

    public WebSocketProperty(String allowedOrigins, String mappingPath, String stompEndpoint,
                             String brokerPrefix, String applicationDestinationPrefix){

        this.allowedOrigins=allowedOrigins;
        this.mappingPath=mappingPath;
        this.stompEndpoint=stompEndpoint;
        this.brokerPrefix=brokerPrefix;
        this.applicationDestinationPrefix=applicationDestinationPrefix;
    }

}
