package org.webservice.config;

import com.zaxxer.hikari.HikariConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;


@Slf4j
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

        hikariConfig.setDriverClassName(dataBaseProperty.getDriverclassname());
        hikariConfig.setJdbcUrl(dataBaseProperty.getUri());
        hikariConfig.setUsername(dataBaseProperty.getUsername());
        hikariConfig.setPassword(dataBaseProperty.getPassword());
        hikariConfig.setMaximumPoolSize(dataBaseProperty.getEtc().getMax_pool_size());
        hikariConfig.setConnectionTimeout(dataBaseProperty.getEtc().getConnection_timeout());
        hikariConfig.setIdleTimeout(dataBaseProperty.getEtc().getIdle_timeout());
        hikariConfig.setMaxLifetime(dataBaseProperty.getEtc().getMax_lifetime());

        return hikariConfig;
    }

    @Bean
    public DataSource dataSource(HikariConfig hikariConfig){
        return new HikariDataSource(hikariConfig);
    }

    //JPA 빈 등록
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource){

        LocalContainerEntityManagerFactoryBean bean=new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource);
        bean.setPackagesToScan("org.webservice.entity");

        HibernateJpaVendorAdapter vendorAdapter=new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(vendorAdapter);

        Properties properties=new Properties();


        properties.setProperty("hibernate.show_sql", String.valueOf(jpaProperty.isShow_sql()));
        properties.setProperty("hibernate.hbm2ddl.auto", jpaProperty.getHb().getDdl_auto());
        properties.setProperty("hibernate.dialect", jpaProperty.getDatabase_platform());
        properties.setProperty("hibernate.format_sql", String.valueOf(jpaProperty.getHb().isFormat_sql()));
        bean.setJpaProperties(properties);


        return bean;
    }
}
