package com.ifi.service.employee;

import com.ifi.data.EmployeeDAO;
import com.ifi.data.exception.EmployeeDataException;
import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.dto.EmployeeDTO;
import com.ifi.entity.Employee;
import com.ifi.service.exception.DeleteEntityException;
import com.ifi.util.mapper.EmployeeMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

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

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws EmployeeSaveException, EmployeeDataException {
        Employee toSave = employeeMapper.employeeDTOtoEmployee(employeeDTO);
        EmployeeDTO result = employeeMapper.employeeToEmployeeDTO(employeeDAO.updateEntity(toSave));
        result.setType(Employee.class.getSimpleName());
        return result;
    }

    @Override
    public void deleteEmployee(String id) throws DeleteEntityException {
        UUID uuid = UUID.fromString(id);
        Employee employee = employeeDAO.deleteEntity(uuid);
        if (employee == null) {
            throw new DeleteEntityException();
        }

    }
}
