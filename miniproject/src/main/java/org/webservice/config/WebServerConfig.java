package org.webservice.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
@EnableConfigurationProperties(WebServerProperty.class)
public class WebServerConfig {
    private final WebServerProperty webServerProperty;

    public WebServerConfig(WebServerProperty webServerProperty) {
        this.webServerProperty = webServerProperty;
    }

    @Bean
    public ServletWebServerFactory servletWebServerFactory() {

        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setPort(webServerProperty.getPort());
        factory.addConnectorCustomizers(connector -> {
            Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
            protocol.setMaxThreads(webServerProperty.getMaxthreads());
            protocol.setMinSpareThreads(webServerProperty.getMinsparethreads());
            protocol.setMaxConnections(webServerProperty.getMaxconnections());
            protocol.setConnectionTimeout(webServerProperty.getConnectiontimeout());
        });

        return factory;
    }

    @Bean
    public Connector connector(){

        Connector connector=new Connector();
        connector.setPort(webServerProperty.getPort());
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        protocol.setMaxThreads(webServerProperty.getMaxthreads());
        protocol.setMinSpareThreads(webServerProperty.getMinsparethreads());
        protocol.setMaxConnections(webServerProperty.getMaxconnections());
        protocol.setConnectionTimeout(webServerProperty.getConnectiontimeout());

        return connector;
    }

}
