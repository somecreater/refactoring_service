package org.webservice.config;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@ConfigurationProperties("spring.mvc")
@Validated
public class SpringProperty2{

    @NotNull
    private view view;
    @NotEmpty
    private String staticPathPattern;

    public SpringProperty2(view view, String staticPathPattern){
        this.view=view;
        this.staticPathPattern=staticPathPattern;
    }

    @Getter
    public static class view{
        @NotEmpty
        private String prefix;
        @NotEmpty
        private String suffix;

        public view(String prefix, String suffix){
            this.prefix=prefix;
            this.suffix=suffix;
        }
    }

}
