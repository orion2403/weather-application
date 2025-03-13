package com.orion.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class RootConfiguration {

    private final Environment environment;

    public RootConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        var sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setHibernateProperties(properties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        var driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(environment.getRequiredProperty("hibernate.connection.driver"));
        driverManagerDataSource.setUrl(environment.getRequiredProperty("hibernate.connection.url"));
        driverManagerDataSource.setUsername(environment.getRequiredProperty("hibernate.connection.username"));
        driverManagerDataSource.setPassword(environment.getRequiredProperty("hibernate.connection.password"));
        return driverManagerDataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        var transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public SpringLiquibase liquibase() {
        var liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:resources/db.changelog/db.changelog-master.yaml");
        liquibase.setDataSource(dataSource());
        return liquibase;
    }

    private Properties properties() {
        var properties = new Properties();
        properties.put("hibernate.connection.pool_size", environment.getRequiredProperty("hibernate.connection.pool_size"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        return properties;
    }
}
