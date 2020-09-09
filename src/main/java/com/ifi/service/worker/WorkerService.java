package com.ifi.service.worker;

import com.ifi.data.exception.EmployeeDataException;
import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.dto.WorkerDTO;
import com.ifi.service.exception.DeleteEntityException;

import javax.ejb.Local;

@Local
public interface WorkerService {
    WorkerDTO addWorker(WorkerDTO workerDTO) throws EmployeeSaveException;

    WorkerDTO updateWorker(WorkerDTO workerDTO) throws EmployeeSaveException, EmployeeDataException;

    void deleteWorker(String workerId) throws DeleteEntityException;
}
