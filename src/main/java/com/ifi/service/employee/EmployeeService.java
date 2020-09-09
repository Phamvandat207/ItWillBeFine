package com.ifi.service.employee;

import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.dto.EmployeeDTO;

import javax.ejb.LocalBean;
import java.util.List;

@LocalBean
public interface EmployeeService {
    List<EmployeeDTO> findAllEmployee();

    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) throws EmployeeSaveException;
}
