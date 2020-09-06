package com.ifi.util.container;

import jakarta.enterprise.inject.Produces;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ApplicationContext {
    @Produces
    @PersistenceContext
    private EntityManager entityManager;
}
