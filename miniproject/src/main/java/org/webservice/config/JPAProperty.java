package org.webservice.config;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@ConfigurationProperties("spring.jpa")
@Validated
public class JPAProperty {

    @NotNull
    private boolean show_sql;
    @NotNull
    private Hibernate hibernate;
    @NotEmpty
    private String database_platform;
    public JPAProperty(boolean show_sql, Hibernate hibernate, String database_platform){
        this.show_sql=show_sql;
        this.hibernate=hibernate;
        this.database_platform=database_platform;
    }
    @Getter
    public static class Hibernate{

        @NotNull
        private boolean show_sql;
        @NotNull
        private boolean format_sql;
        @NotEmpty
        private String ddl_auto;

        public Hibernate(boolean show_sql, boolean format_sql, String ddl_auto){
            this.show_sql=show_sql;
            this.format_sql=format_sql;
            this.ddl_auto=ddl_auto;
        }

    }
}
