package com.ifi.util.mapper;

import com.ifi.dto.EngineerDTO;
import com.ifi.entity.Engineer;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface EngineerMapper {
    EngineerDTO engineerToEngineerDTO(Engineer engineer);

    Engineer engineerDTOToEngineer(EngineerDTO engineerDTO);

    @InheritConfiguration
    void updateEngineer(EngineerDTO engineerDTO, @MappingTarget Engineer engineer);
}
