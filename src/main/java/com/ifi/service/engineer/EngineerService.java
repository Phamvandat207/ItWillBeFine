package com.ifi.service.engineer;

import com.ifi.data.exception.EmployeeDataException;
import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.dto.EngineerDTO;
import com.ifi.service.exception.DeleteEntityException;

import javax.ejb.Local;

@Local
public interface EngineerService {
    EngineerDTO addNewEngineer(EngineerDTO engineerDTO) throws EmployeeSaveException;

    EngineerDTO updateEngineer(EngineerDTO engineerDTO) throws EmployeeSaveException, EmployeeDataException;

    void deleteEngineer(String engineerId) throws DeleteEntityException;
}
