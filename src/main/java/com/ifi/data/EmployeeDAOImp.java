package com.ifi.data;

import com.ifi.data.exception.EmployeeDataException;
import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.entity.Employee;
import com.ifi.entity.Employee_;
import jakarta.enterprise.context.ApplicationScoped;
import org.reflections.Reflections;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
@Named
public class EmployeeDAOImp implements EmployeeDAO {
    @Inject
    EntityManager entityManager;

    @Resource
    UserTransaction userTransaction;

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
    public <T extends Employee> T findEntityByID(UUID id) {
        Employee employeeFound = entityManager.find(Employee.class, id);
        T result = null;
        if (employeeFound != null) {
            entityManager.detach(employeeFound);
            result = getSubType(employeeFound);
        }
        return result;
    }

    @Override
    public <T extends Employee> T findEntityByID(UUID id, Class<T> clazz) {
        T employee = entityManager.find(clazz, id);
        if (employee != null) {
            entityManager.detach(employee);
        }
        return employee;
    }

    @Override
    public <T extends Employee> T addNewEntity(T employee) throws EmployeeSaveException {
        if (employee.getId() != null) {
            throw new EmployeeSaveException();
        }
        try {
            userTransaction.begin();
            entityManager.persist(employee);
            entityManager.flush();
            userTransaction.commit();
        } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | RollbackException e) {
            e.printStackTrace();
        }
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

    @Override
    public <T extends Employee> T updateEntity(T employee) throws EmployeeSaveException, EmployeeDataException {
        Employee employeeFound;
        T result = null;
        if (employee.getId() == null) {
            throw new EmployeeDataException();
        } else {
            employeeFound = entityManager.find(employee.getClass(), employee.getId());
            if (employeeFound == null) {
                throw new EmployeeSaveException();
            }
        }
        try {
            userTransaction.begin();
            result = entityManager.merge(employee);
            userTransaction.commit();
        } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | RollbackException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public <T extends Employee> T deleteEntity(T employee) {
        Class<? extends Employee> clazz = employee.getClass();
        Employee employeeFound = entityManager.find(clazz, employee.getId());
        removeEntity(employeeFound);
        return employee;
    }

    @Override
    public <T extends Employee> T deleteEntity(UUID id) {
        Employee employeeFound = entityManager.find(Employee.class, id);
        removeEntity(employeeFound);
        return getSubType(employeeFound);
    }

    private void removeEntity(Employee employeeFound) {
        if (employeeFound == null) {
            throw new EntityNotFoundException();
        }
        try {
            userTransaction.begin();
            entityManager.remove(
                    entityManager.contains(employeeFound) ? employeeFound : entityManager.merge(employeeFound)
            );
            userTransaction.commit();
        } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | RollbackException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends Employee> T getSubType(Employee employeeFound) {
        Reflections reflections = new Reflections(employeeFound.getClass().getPackage().getName());
        Set<Class<? extends Employee>> classSet = reflections.getSubTypesOf(Employee.class);
        for (Class<? extends Employee> clazz : classSet) {
            if (employeeFound.getClass().isAssignableFrom(clazz)) {
                return (T) employeeFound;
            }
        }
        return null;
    }
}
