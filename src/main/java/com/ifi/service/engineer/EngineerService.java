package com.ifi.service.engineer;

import com.ifi.data.exception.EmployeeDataException;
import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.dto.EngineerDTO;

import javax.ejb.Local;

@Local
public interface EngineerService {
    EngineerDTO addNewEngineer(EngineerDTO engineerDTO) throws EmployeeSaveException;

    EngineerDTO updateEngineer(EngineerDTO engineerDTO) throws EmployeeSaveException, EmployeeDataException;
}
