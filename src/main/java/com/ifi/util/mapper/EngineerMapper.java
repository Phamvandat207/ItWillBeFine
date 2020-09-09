package com.ifi.util.mapper;

import com.ifi.dto.EngineerDTO;
import com.ifi.entity.Engineer;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface EngineerMapper {

    @Mapping(target = "engineerMonthlyWage", source = "monthlyWage")
    @Mapping(target = "engineerAllowance", source = "allowance")
    EngineerDTO engineerToEngineerDTO(Engineer engineer);

    @Mapping(target = "monthlyWage", source = "engineerMonthlyWage")
    @Mapping(target = "allowance", source = "engineerAllowance")
    Engineer engineerDTOToEngineer(EngineerDTO engineerDTO);

    @InheritConfiguration
    void updateEngineer(EngineerDTO engineerDTO, @MappingTarget Engineer engineer);
}
