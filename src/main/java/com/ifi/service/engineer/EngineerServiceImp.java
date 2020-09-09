package com.ifi.service.engineer;

import com.ifi.data.EmployeeDAO;
import com.ifi.data.exception.EmployeeDataException;
import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.dto.EngineerDTO;
import com.ifi.entity.Engineer;
import com.ifi.service.exception.DeleteEntityException;
import com.ifi.util.mapper.EmployeeMapper;

import javax.inject.Inject;
import java.util.UUID;

public class EngineerServiceImp implements EngineerService {
    @Inject
    EmployeeDAO employeeDAO;

    @Inject
    EmployeeMapper employeeMapper;

    @Override
    public EngineerDTO addNewEngineer(EngineerDTO engineerDTO) throws EmployeeSaveException {
        Engineer toAdd = employeeMapper.engineerDTOToEngineer(engineerDTO);
        EngineerDTO added = employeeMapper.engineerToEngineerDTO(employeeDAO.addNewEntity(toAdd));
        added.setType(Engineer.class.getSimpleName());
        return added;
    }

    @Override
    public EngineerDTO updateEngineer(EngineerDTO engineerDTO) throws EmployeeSaveException, EmployeeDataException {
        Engineer toUpdate = employeeMapper.engineerDTOToEngineer(engineerDTO);
        EngineerDTO updated = employeeMapper.engineerToEngineerDTO(employeeDAO.updateEntity(toUpdate));
        updated.setType(Engineer.class.getSimpleName());
        return updated;
    }

    @Override
    public void deleteEngineer(String engineerId) throws DeleteEntityException {
        UUID engineerUUID = UUID.fromString(engineerId);
        Engineer deleted = employeeDAO.deleteEntity(engineerUUID);
        if (deleted == null) {
            throw new DeleteEntityException();
        }
    }
}
