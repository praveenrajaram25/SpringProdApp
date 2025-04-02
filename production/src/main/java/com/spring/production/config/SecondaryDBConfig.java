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
        basePackages = "com.spring.production.repository.secondary",
        entityManagerFactoryRef = "secondaryEntityManagerFactory",
        transactionManagerRef = "secondaryTransactionManager"
)
public class SecondaryDBConfig {

    private static final Logger logger = LogManager.getLogger(SecondaryDBConfig.class);

    @Autowired
    private Environment env;



    @Bean(name = "secondaryDataSource")
    //@ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                env.getProperty("spring.datasource.secondary.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.secondary.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.secondary.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.secondary.password"));
        return dataSource;
        //return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("secondaryDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)

                .packages("com.spring.production.model.secondary")
                .persistenceUnit("secondary")
                .build();
    }

    @Bean(name = "secondaryTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("secondaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        logger.info("Loading Secondary DB Configs...");
        return new JpaTransactionManager(entityManagerFactory);
    }
}
