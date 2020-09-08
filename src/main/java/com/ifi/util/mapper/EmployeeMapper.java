package com.ifi.util.mapper;

import com.ifi.dto.EmployeeDTO;
import com.ifi.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface EmployeeMapper {

    @Mapping(source = "name", target = "name")
    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    List<EmployeeDTO> employeeListToEmployeeDTOList(List<Employee> employeeList);
}
