package com.ifi.service.employee;

import com.ifi.data.exception.EmployeeDataException;
import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.dto.EmployeeDTO;
import com.ifi.service.exception.DeleteEntityException;

import javax.ejb.LocalBean;
import java.util.List;
import java.util.UUID;

@LocalBean
public interface EmployeeService {
    List<EmployeeDTO> findAllEmployee();

    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) throws EmployeeSaveException;

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws EmployeeSaveException, EmployeeDataException;

    void deleteEmployee(String id) throws DeleteEntityException;
}
