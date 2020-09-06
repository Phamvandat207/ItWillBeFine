package com.ifi.data;

import com.ifi.entity.Employee;
import com.ifi.entity.Employee_;
import jakarta.enterprise.context.ApplicationScoped;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@ApplicationScoped
@Named
public class EmployeeDAOImp implements EmployeeDAO {
    @Inject
    EntityManager entityManager;

    @PostConstruct
    public void init() {
        System.out.println("EmployeeDAO loaded!");
    }

    @Override
    public List<Employee> findAllEntity() {
        CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = queryBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = query.from(Employee.class);
        query.orderBy(queryBuilder.desc(employeeRoot.get(Employee_.joinedDate)));
        TypedQuery<Employee> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public Employee findEntityByID(UUID id) {
        return entityManager.find(Employee.class, id);
    }
}
