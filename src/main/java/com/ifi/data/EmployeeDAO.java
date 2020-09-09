package com.ifi.data;

import com.ifi.entity.Employee;
import com.ifi.data.exception.EmployeeDataException;
import com.ifi.data.exception.EmployeeSaveException;

import javax.ejb.Local;
import java.util.List;
import java.util.UUID;

@Local
public interface EmployeeDAO {
    List<Employee> findAllEntity();

    <T extends Employee> T findEntityByID(UUID id);

    <T extends Employee> T findEntityByID(UUID id, Class<T> clazz);

    <T extends Employee> T addNewEntity(T employee) throws EmployeeSaveException;

    long getCurrentSize();

    <T extends Employee> T updateEntity(T employee) throws EmployeeSaveException, EmployeeDataException;

    <T extends Employee> T deleteEntity(T employee);

    <T extends Employee> T deleteEntity(UUID id);
}
