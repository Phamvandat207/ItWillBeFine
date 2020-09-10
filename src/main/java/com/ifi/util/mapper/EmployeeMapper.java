package com.ifi.util.mapper;

import com.ifi.dto.EmployeeDTO;
import com.ifi.dto.EngineerDTO;
import com.ifi.dto.WorkerDTO;
import com.ifi.entity.Employee;
import com.ifi.entity.Engineer;
import com.ifi.entity.Worker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "cdi", imports = BigDecimal.class)
public interface EmployeeMapper {

    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    Employee employeeDTOtoEmployee(EmployeeDTO employeeDTO);

    default List<EmployeeDTO> employeeListToEmployeeDTOList(List<Employee> employeeList) {
        return employeeList.stream().map(employee -> {
            if (employee instanceof Engineer) {
                EngineerDTO engineerDTO = engineerToEngineerDTO((Engineer) employee);
                engineerDTO.setType(Engineer.class.getSimpleName());
                return engineerDTO;
            } else if (employee instanceof Worker) {
                WorkerDTO workerDTO = workerToWorkerDTO((Worker) employee);
                workerDTO.setType(Worker.class.getSimpleName());
                return workerDTO;
            } else {
                EmployeeDTO employeeDTO = employeeToEmployeeDTO(employee);
                employeeDTO.setType(Employee.class.getSimpleName());
                return employeeDTO;
            }
        }).collect(Collectors.toList());
    }

    @Mapping(target = "engineerMonthlyWage", source = "monthlyWage")
    @Mapping(target = "engineerAllowance", source = "allowance")
    EngineerDTO engineerToEngineerDTO(Engineer engineer);

    @Mapping(target = "monthlyWage", source = "engineerMonthlyWage", defaultExpression = "java(BigDecimal.ZERO)")
    @Mapping(target = "allowance", source = "engineerAllowance", defaultExpression = "java(BigDecimal.ZERO)")
    Engineer engineerDTOToEngineer(EngineerDTO engineerDTO);

    @Mapping(target = "workerHourlyRating", source = "hourlyRating")
    WorkerDTO workerToWorkerDTO(Worker worker);

    @Mapping(target = "hourlyRating", source = "workerHourlyRating", defaultExpression = "java(BigDecimal.ZERO)")
    Worker workerDTOToWorker(WorkerDTO workerDTO);

}
