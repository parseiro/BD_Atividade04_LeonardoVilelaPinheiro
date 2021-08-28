package com.curso.demo;

import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.curso.repository")
@EnableTransactionManagement
@Profile("dev")
public class SpringDataConf {
    @Bean
    public DataSource dataSource() {
        BoneCPDataSource ds = new BoneCPDataSource();

        ds.setUser("leopinheiro");
        ds.setPassword("T72Fyub&r4jilKEW");

        ds.setJdbcUrl("jdbc:mysql://localhost/curso-spring-db");
        ds.setDriverClass("com.mysql.cj.jdbc.Driver");
        return ds;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        var factory = new LocalContainerEntityManagerFactoryBean();

        var vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(true);

        factory.setDataSource(dataSource());
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.curso.entity");

        var jpaProperties = new Properties();
//        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
//        jpaProperties.put("hibernate.hbm2ddl.auto", "validate");
        jpaProperties.put("hibernate.hbm2ddl.auto", "create");
        factory.setJpaProperties(jpaProperties);

        factory.afterPropertiesSet();


        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        var manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory());
        manager.setJpaDialect(new HibernateJpaDialect());

        return manager;
    }

}
