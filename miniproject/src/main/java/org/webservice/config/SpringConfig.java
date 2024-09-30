package org.webservice.config;

import jakarta.servlet.MultipartConfigElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@Slf4j
@EnableWebMvc
@ComponentScan(basePackages = "org.webservice.controller")
public class SpringConfig implements WebMvcConfigurer {


    private final SpringProperty springProperty;
    private final SpringProperty2 springProperty2;
    private final SpringProperty3 springProperty3;

    public SpringConfig(SpringProperty springProperty, SpringProperty2 springProperty2,
                        SpringProperty3 springProperty3) {
        this.springProperty = springProperty;
        this.springProperty2 = springProperty2;
        this.springProperty3 = springProperty3;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(springProperty2.getStaticPathPattern())
                .addResourceLocations(springProperty.getResources().getStaticLocations());
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){
        InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
        viewResolver.setPrefix(springProperty2.getView().getPrefix());
        viewResolver.setSuffix(springProperty2.getView().getSuffix());
        registry.viewResolver(viewResolver);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory multipartConfigFactory=new MultipartConfigFactory();
        multipartConfigFactory.setMaxFileSize(springProperty3.getMaxFileSize());
        multipartConfigFactory.setMaxRequestSize(springProperty3.getMaxRequestSize());
        return multipartConfigFactory.createMultipartConfig();
    }
    @Bean
    public MultipartResolver multipartResolver() {
        StandardServletMultipartResolver standardServletMultipartResolver=new StandardServletMultipartResolver();
        return standardServletMultipartResolver;
    }


}
