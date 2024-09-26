package org.webservice.config;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@ConfigurationProperties("spring.jpa")
@Validated
public class JPAProperty {

    @NotEmpty
    private boolean show_sql;
    @NotEmpty
    private hibernate hb;
    @NotEmpty
    private String database_platform;

    public JPAProperty(boolean show_sql, hibernate hb, String database_platform){
        this.show_sql=show_sql;
        this.hb=hb;
        this.database_platform=database_platform;
    }
    @Getter
    public static class hibernate{

        @NotEmpty
        private boolean show_sql;
        @NotEmpty
        private boolean format_sql;
        @NotEmpty
        private String ddl_auto;

        public hibernate(boolean show_sql, boolean format_sql, String ddl_auto){
            this.show_sql=show_sql;
            this.format_sql=format_sql;
            this.ddl_auto=ddl_auto;
        }

    }
}
