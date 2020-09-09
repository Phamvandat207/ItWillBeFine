package com.ifi.service.employee;

import com.ifi.data.EmployeeDAO;
import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.dto.EmployeeDTO;
import com.ifi.entity.Employee;
import com.ifi.util.mapper.EmployeeMapper;

import javax.inject.Inject;
import java.util.List;

public class EmployeeServiceImp implements EmployeeService {
    @Inject
    EmployeeDAO employeeDAO;

    @Inject
    EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDTO> findAllEmployee() {
        List<Employee> employeeFound = employeeDAO.findAllEntity();
        return employeeMapper.employeeListToEmployeeDTOList(employeeFound);
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) throws EmployeeSaveException {
        Employee employeeToAdd = employeeMapper.employeeDTOtoEmployee(employeeDTO);
        EmployeeDTO result = employeeMapper.employeeToEmployeeDTO(employeeDAO.addNewEntity(employeeToAdd));
        result.setType(Employee.class.getSimpleName());
        return result;
    }
}
