package com.spring.production.config;


import com.spring.production.serviceimpl.SpringServiceImpl;
import jakarta.persistence.EntityManagerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.spring.production.repository.primary",
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryDBConfig {

    private static final Logger logger = LogManager.getLogger(PrimaryDBConfig.class);
    @Autowired
    private Environment env;

    @Primary
    @Bean(name = "primaryDataSource")
    //@ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource dataSource() {
       // return DataSourceBuilder.create().build();

        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                env.getProperty("spring.datasource.primary.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.primary.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.primary.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.primary.password"));

        return dataSource;
    }

    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("primaryDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.spring.production.model.primary")
                .persistenceUnit("primary")
                .build();
    }

    @Primary
    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("primaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {

        logger.info("Loading Primary DB Configs...");
        return new JpaTransactionManager(entityManagerFactory);
    }

}
