package com.ifi.service.engineer;

import com.ifi.data.EmployeeDAO;
import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.dto.EngineerDTO;
import com.ifi.entity.Engineer;
import com.ifi.util.mapper.EmployeeMapper;

import javax.inject.Inject;

public class EngineerServiceImp implements EngineerService {
    @Inject
    EmployeeDAO employeeDAO;

    @Inject
    EmployeeMapper employeeMapper;

    @Override
    public EngineerDTO addNewEngineer(EngineerDTO engineerDTO) throws EmployeeSaveException {
        Engineer toAdd = employeeMapper.engineerDTOToEngineer(engineerDTO);
        EngineerDTO result = employeeMapper.engineerToEngineerDTO(employeeDAO.addNewEntity(toAdd));
        result.setType(Engineer.class.getSimpleName());
        return result;
    }
}
