package com.ifi.service.worker;

import com.ifi.data.EmployeeDAO;
import com.ifi.data.exception.EmployeeDataException;
import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.dto.WorkerDTO;
import com.ifi.entity.Worker;
import com.ifi.service.exception.DeleteEntityException;
import com.ifi.util.mapper.EmployeeMapper;

import javax.inject.Inject;
import java.util.UUID;

public class WorkerServiceImp implements WorkerService {
    @Inject
    EmployeeDAO employeeDAO;

    @Inject
    EmployeeMapper employeeMapper;

    @Override
    public WorkerDTO addWorker(WorkerDTO workerDTO) throws EmployeeSaveException {
        Worker toAdd = employeeMapper.workerDTOToWorker(workerDTO);
        WorkerDTO added = employeeMapper.workerToWorkerDTO(employeeDAO.addNewEntity(toAdd));
        added.setType(Worker.class.getSimpleName());
        return added;
    }

    @Override
    public WorkerDTO updateWorker(WorkerDTO workerDTO) throws EmployeeSaveException, EmployeeDataException {
        Worker toUpdate = employeeMapper.workerDTOToWorker(workerDTO);
        WorkerDTO updated = employeeMapper.workerToWorkerDTO(employeeDAO.updateEntity(toUpdate));
        updated.setType(Worker.class.getSimpleName());
        return updated;
    }

    @Override
    public void deleteWorker(String workerId) throws DeleteEntityException {
        UUID workerUUID = UUID.fromString(workerId);
        Worker deleted = employeeDAO.deleteEntity(workerUUID);
        if (deleted == null) {
            throw new DeleteEntityException();
        }
    }
}
