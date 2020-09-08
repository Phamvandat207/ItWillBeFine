package com.ifi.util.mapper;

import com.ifi.dto.WorkerDTO;
import com.ifi.entity.Worker;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface WorkerMapper {
    @Mapping(target = "workerId", source = "id")
    @Mapping(target = "workerName", source = "name")
    @Mapping(target = "workerGender", source = "gender")
    @Mapping(target = "workerJoinedDate", source = "joinedDate")
    @Mapping(target = "workerDateOfBirth", source = "dateOfBirth")
    @Mapping(target = "workerHourlyRating", source = "hourlyRating")
    WorkerDTO workerToWorkerDTO(Worker worker);

    @Mapping(target = "id", source = "workerId")
    @Mapping(target = "name", source = "workerName")
    @Mapping(target = "gender", source = "workerGender")
    @Mapping(target = "joinedDate", source = "workerJoinedDate")
    @Mapping(target = "dateOfBirth", source = "workerDateOfBirth")
    @Mapping(target = "hourlyRating", source = "workerHourlyRating")
    Worker workerDTOToWorker(WorkerDTO workerDTO);

    @InheritConfiguration
    void updateWorker(WorkerDTO workerDTO, @MappingTarget Worker worker);
}
