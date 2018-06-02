package ru.levelp.myapp.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan("ru.levelp.myapp")
@EnableWebMvc
@EnableTransactionManagement
public class TestConfiguration {
    @Bean("entityManagerFactory")
    public EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("TestPersistenceUnit");
    }

//    @Bean("transactionManager")
//    public PlatformTransactionManager getTransactionManager(EntityManagerFactory emf) {
//        return new JpaTransactionManager(emf);
//    }
}
