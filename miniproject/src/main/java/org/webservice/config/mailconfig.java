package org.webservice.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:application.properties")
public class mailconfig {
	/*
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private String port;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean auth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean enable;    
    */
    /*
    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
    private boolean required;
    @Value("${spring.mail.properties.mail.smtp.connectiontimeout}")
    private int cntimeout;
    @Value("${spring.mail.properties.mail.smtp.timeout}")
    private int timeout;
    @Value("${spring.mail.properties.mail.smtp.writetimeout}")
    private int wtimeout;
    @Value("${spring.mail.properties.mail.transport.protocol}")
    private String protocol;
    */
	
    @Bean
    public JavaMailSender getjavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties properties=new Properties();
        /*
        properties.put("mail.smtp.auth", auth);
        properties.put("mail.smtp.starttls.enable", enable);
        properties.put("mail.smtp.starttls.required", required);
        properties.put("mail.smtp.connectiontimeout", cntimeout);
        properties.put("mail.smtp.timeout", timeout);
        properties.put("mail.smtp.writetimeout", wtimeout);
        properties.put("mail.transport.protocol", protocol);
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setJavaMailProperties(properties);
        */
        /*
        mailSender.setHost("smtp.naver.com");
        mailSender.setPort(465);
        mailSender.setUsername("naverid");
        mailSender.setPassword("naverpassword");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.naver.com");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		*/
        /*
        mailSender.setHost(host);
        mailSender.setProtocol("smtp");
        mailSender.setPort(Integer.parseInt(port));
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        //properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        mailSender.setJavaMailProperties(properties);
        
        mailSender.setJavaMailProperties(properties);
        */
        return mailSender;
        
    }
}
