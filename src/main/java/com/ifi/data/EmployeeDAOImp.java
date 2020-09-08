package com.ifi.data;

import com.ifi.entity.Employee;
import com.ifi.entity.Employee_;
import com.ifi.data.exception.EmployeeDataException;
import com.ifi.data.exception.EmployeeSaveException;
import jakarta.enterprise.context.ApplicationScoped;
import org.reflections.Reflections;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
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

    @Override
    public <T extends Employee> T updateEntity(T employee) throws EmployeeSaveException, EmployeeDataException {
        Employee employeeFound;
        if (employee.getId() == null) {
            throw new EmployeeDataException();
        } else {
            employeeFound = entityManager.find(employee.getClass(), employee.getId());
            if (employeeFound == null) {
                throw new EmployeeSaveException();
            }
        }
        entityManager.getTransaction().begin();
        T result = entityManager.merge(employee);
        entityManager.getTransaction().commit();
        return result;
    }

    @Override
    public <T extends Employee> T deleteEntity(T employee) {
        Class<? extends Employee> clazz = employee.getClass();
        Employee employeeFound = entityManager.find(clazz, employee.getId());
        if (employeeFound == null) {
            throw new EntityNotFoundException();
        }
        entityManager.getTransaction().begin();
        entityManager.remove(employeeFound);
        entityManager.getTransaction().commit();
        return employee;
    }

    @Override
    public <T extends Employee> T deleteEntity(UUID id) {
        Employee employeeFound = entityManager.find(Employee.class, id);
        if (employeeFound == null) {
            throw new EntityNotFoundException();
        }
        entityManager.getTransaction().begin();
        entityManager.remove(employeeFound);
        entityManager.getTransaction().commit();
        return getSubType(employeeFound);
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
