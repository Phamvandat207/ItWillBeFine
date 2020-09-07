package com.ifi.util.container;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BeanResources {
    @Produces
    @PersistenceContext
    private EntityManager entityManager;
}
