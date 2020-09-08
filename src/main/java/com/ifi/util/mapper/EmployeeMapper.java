package com.ifi.util.mapper;

import com.ifi.dto.EmployeeDTO;
import com.ifi.entity.Employee;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface EmployeeMapper {
    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    List<EmployeeDTO> employeeListToEmployeeDTOList(List<Employee> employeeList);

    Employee employeeDTOtoEmployee(EmployeeDTO employeeDTO);

    @InheritConfiguration
    void updateEmployee(EmployeeDTO employeeDTO, @MappingTarget Employee employee);

}
