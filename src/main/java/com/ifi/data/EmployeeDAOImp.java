package com.ifi.data;

import com.ifi.entity.Employee;
import com.ifi.entity.Employee_;
import com.ifi.util.exception.IdNotNullException;
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

    @Override
    public <T extends Employee> T findEntityByID(UUID id, Class<T> clazz) {
        return entityManager.find(clazz, id);
    }

    @Override
    public <T extends Employee> T addNewEntity(T employee) throws IdNotNullException {
        if (employee.getId() != null) {
            throw new IdNotNullException();
        }
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return employee;
    }

    @Override
    public long getCurrentSize() {
        CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = queryBuilder.createQuery(Long.class);
        query.select(queryBuilder.count(query.from(Employee.class)));
        TypedQuery<Long> typedQuery = entityManager.createQuery(query);
        return typedQuery.getSingleResult();
    }
}
