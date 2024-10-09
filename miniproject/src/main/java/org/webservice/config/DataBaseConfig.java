package org.webservice.config;

import com.zaxxer.hikari.HikariConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;


@Slf4j
@Configuration
@EnableJpaRepositories(basePackages = {"org.webservice.repository"})
@EnableConfigurationProperties({DataBaseProperty.class, JPAProperty.class})
public class DataBaseConfig {

    private final DataBaseProperty dataBaseProperty;
    private final JPAProperty jpaProperty;
    public DataBaseConfig(DataBaseProperty dataBaseProperty, JPAProperty jpaProperty){
        this.dataBaseProperty=dataBaseProperty;
        this.jpaProperty=jpaProperty;
    }

    //hikaricp 빈 등록
    @Bean
    public HikariConfig hikariConfig(){
        HikariConfig hikariConfig=new HikariConfig();
        hikariConfig.setDriverClassName(dataBaseProperty.getDriverClassName());
        hikariConfig.setJdbcUrl(dataBaseProperty.getUrl());
        hikariConfig.setUsername(dataBaseProperty.getUsername());
        hikariConfig.setPassword(dataBaseProperty.getPassword());
        hikariConfig.setMaximumPoolSize(dataBaseProperty.getHikari().getMaxPoolSize());
        hikariConfig.setConnectionTimeout(dataBaseProperty.getHikari().getConnectionTimeout());
        hikariConfig.setIdleTimeout(dataBaseProperty.getHikari().getIdleTimeout());
        hikariConfig.setMaxLifetime(dataBaseProperty.getHikari().getMaxLifetime());

        return hikariConfig;
    }

    @Bean
    public DataSource dataSource(HikariConfig hikariConfig){
        return new HikariDataSource(hikariConfig);
    }

    //JPA 빈 등록
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource){

        LocalContainerEntityManagerFactoryBean bean=new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource);
        bean.setPackagesToScan("org.webservice.entity");

        HibernateJpaVendorAdapter vendorAdapter=new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(jpaProperty.isShow_sql());
        bean.setJpaVendorAdapter(vendorAdapter);

        Properties properties=new Properties();
        properties.setProperty("hibernate.show_sql", String.valueOf(jpaProperty.isShow_sql()));
        properties.setProperty("hibernate.hbm2ddl.auto", jpaProperty.getHibernate().getDdl_auto());
        properties.setProperty("hibernate.dialect", jpaProperty.getDatabase_platform());
        properties.setProperty("hibernate.format_sql", String.valueOf(jpaProperty.getHibernate().isFormat_sql()));
        bean.setJpaProperties(properties);

        return bean;
    }
}
