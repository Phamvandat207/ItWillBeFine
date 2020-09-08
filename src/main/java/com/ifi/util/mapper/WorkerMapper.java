package com.ifi.util.mapper;

import com.ifi.dto.WorkerDTO;
import com.ifi.entity.Worker;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface WorkerMapper {
    WorkerDTO workerToWorkerDTO(Worker worker);

    Worker workerDTOToWorker(WorkerDTO workerDTO);

    @InheritConfiguration
    void updateWorker(WorkerDTO workerDTO, @MappingTarget Worker worker);
}
