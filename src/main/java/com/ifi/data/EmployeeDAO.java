package com.ifi.data;

import com.ifi.entity.Employee;

import javax.ejb.Local;
import java.util.List;
import java.util.UUID;

@Local
public interface EmployeeDAO {
    List<Employee> findAllEntity();

    Employee findEntityByID(UUID id);

    <T extends Employee> T findEntityByID(UUID id, Class<T> clazz);

    <T extends Employee> T addNewEntity(T employee);

    long getCurrentSize();
}
