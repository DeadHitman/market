package ru.geekbrains.homework.config;

import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.geekbrains.homework.entity.Product;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

@Component
@Qualifier("EntityManager")
public class EntityManager {

    private final EntityManagerFactory factory;
    private final javax.persistence.EntityManager em;

    public EntityManager() {
        factory = new Configuration()
                .configure("hibernate.xml")
                .buildSessionFactory();
        this.em = factory.createEntityManager();
    }

    public EntityManagerFactory getFactory() {
        return factory;
    }

    public javax.persistence.EntityManager getEm() {
        return em;
    }

}

