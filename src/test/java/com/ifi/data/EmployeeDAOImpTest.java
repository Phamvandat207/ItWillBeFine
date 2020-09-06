package com.ifi.data;

import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@EnableAutoWeld
@AddBeanClasses(EmployeeDAOImp.class)
class EmployeeDAOImpTest {
    @ApplicationScoped
    @Produces
    EntityManager produceEntityManager() {
        return Mockito.mock(EntityManager.class);
    }

    @Inject
    EmployeeDAO employeeDAO;

    @Test
    @DisplayName("Test Framework Error")
    void showFrameworkStatus() {
        System.out.println("Test initiated! CDI successful!");
    }
}
