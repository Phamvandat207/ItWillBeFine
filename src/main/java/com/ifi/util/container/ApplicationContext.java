package com.ifi.util.container;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class ApplicationContext {
    @Produces
    @PersistenceContext
    private EntityManager entityManager;
}
