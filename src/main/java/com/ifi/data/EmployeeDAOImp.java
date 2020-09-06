package com.ifi.data;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class EmployeeDAOImp implements EmployeeDAO {
    @Inject
    EntityManager entityManager;

}
