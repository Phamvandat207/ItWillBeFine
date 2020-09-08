package com.ifi.util.mapper;

import com.ifi.dto.EngineerDTO;
import com.ifi.entity.Engineer;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface EngineerMapper {
    @Mapping(target = "engineerId", source = "id")
    @Mapping(target = "engineerName", source = "name")
    @Mapping(target = "engineerGender", source = "gender")
    @Mapping(target = "engineerJoinedDate", source = "joinedDate")
    @Mapping(target = "engineerDateOfBirth", source = "dateOfBirth")
    @Mapping(target = "engineerMonthlyWage", source = "monthlyWage")
    @Mapping(target = "engineerAllowance", source = "allowance")
    EngineerDTO engineerToEngineerDTO(Engineer engineer);

    @Mapping(target = "id", source = "engineerId")
    @Mapping(target = "name", source = "engineerName")
    @Mapping(target = "gender", source = "engineerGender")
    @Mapping(target = "joinedDate", source = "engineerJoinedDate")
    @Mapping(target = "dateOfBirth", source = "engineerDateOfBirth")
    @Mapping(target = "monthlyWage", source = "engineerMonthlyWage")
    @Mapping(target = "allowance", source = "engineerAllowance")
    Engineer engineerDTOToEngineer(EngineerDTO engineerDTO);

    @InheritConfiguration
    void updateEngineer(EngineerDTO engineerDTO, @MappingTarget Engineer engineer);
}
