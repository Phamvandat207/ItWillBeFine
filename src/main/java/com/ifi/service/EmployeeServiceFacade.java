package com.ifi.service;

import com.ifi.data.exception.EmployeeDataException;
import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.dto.EmployeeDTO;
import com.ifi.entity.Employee;

import java.util.List;

public interface EmployeeServiceFacade {
    List<EmployeeDTO> findAll();

    EmployeeDTO addEmployee(EmployeeDTO employeeDTO) throws EmployeeSaveException;

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws EmployeeSaveException, EmployeeDataException;

    void deleteEmployee(EmployeeDTO employeeDTO);
}
