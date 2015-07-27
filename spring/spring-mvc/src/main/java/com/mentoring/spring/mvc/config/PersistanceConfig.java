package com.mentoring.spring.mvc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by azargan on 22.07.15.
 */
@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.mentoring.spring.mvc.repository"})
public class PersistanceConfig {

    private static final String[] packagesToScan = {"com.mentoring.spring.mvc.entity"};
    @Value("${db.driver}") private String dbDriver;
    @Value("${db.url}") private String dbUrl;
    @Value("${db.username}") private String dbUsername;
    @Value("${db.password}") private String dbPassword;
    @Value("${hibernate.dialect}") private String hibernateDialect;
    @Value("${hibernate.hbm2ddl.auto}") private String hibernateDdlMode;
    @Value("${hibernate.ejb.naming_strategy}") private String hibernateNamingStrategy;
    @Value("${hibernate.show_sql}") private String hibernateShowSqlMode;
    @Value("${hibernate.format_sql}") private String hibernateFormatSqlMode;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {

        final HikariConfig config = new HikariConfig();

        config.setDriverClassName(dbDriver);
        config.setJdbcUrl(dbUrl);
        config.setUsername(dbUsername);
        config.setPassword(dbPassword);

        return new HikariDataSource(config);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

        emf.setDataSource(dataSource());
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setPackagesToScan(packagesToScan);
        emf.setJpaProperties(getAdditionalProperties());

        return emf;
    }

    @Bean
    @Autowired
    public JpaTransactionManager transactionManager(final EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    private Properties getAdditionalProperties () {

        final Properties properties = new Properties();

        properties.setProperty("hibernate.dialect", hibernateDialect);
        properties.setProperty("hibernate.hbm2ddl.auto", hibernateDdlMode);
        properties.setProperty("hibernate.ejb.naming_strategy", hibernateNamingStrategy);
        properties.setProperty("hibernate.show_sql", hibernateShowSqlMode);
        properties.setProperty("hibernate.format_sql", hibernateFormatSqlMode);

        return properties;
    }

}
