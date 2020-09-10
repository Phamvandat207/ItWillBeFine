package com.ifi.service;

import com.ifi.data.exception.EmployeeDataException;
import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.dto.EmployeeDTO;
import com.ifi.dto.EngineerDTO;
import com.ifi.dto.WorkerDTO;
import com.ifi.service.employee.EmployeeService;
import com.ifi.service.engineer.EngineerService;
import com.ifi.service.exception.DeleteEntityException;
import com.ifi.service.worker.WorkerService;

import javax.inject.Inject;
import java.util.List;

public class EmployeeServiceFaceImp implements EmployeeServiceFacade {
    @Inject
    EmployeeService employeeService;

    @Inject
    EngineerService engineerService;

    @Inject
    WorkerService workerService;

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeService.findAllEmployee();
    }

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) throws EmployeeSaveException {
        if (employeeDTO instanceof WorkerDTO) {
            return workerService.addWorker((WorkerDTO) employeeDTO);
        } else if (employeeDTO instanceof EngineerDTO) {
            return engineerService.addNewEngineer((EngineerDTO) employeeDTO);
        }
        return employeeService.createNewEmployee(employeeDTO);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws EmployeeSaveException, EmployeeDataException {
        if (employeeDTO instanceof WorkerDTO) {
            return workerService.updateWorker((WorkerDTO) employeeDTO);
        } else if (employeeDTO instanceof EngineerDTO) {
            return engineerService.updateEngineer((EngineerDTO) employeeDTO);
        }
        return employeeService.updateEmployee(employeeDTO);
    }

    @Override
    public void deleteEmployee(String employeeId) throws DeleteEntityException {
        employeeService.deleteEmployee(employeeId);
    }
}
