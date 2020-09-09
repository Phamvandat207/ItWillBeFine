package com.ifi.rest;

import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.dto.EngineerDTO;
import com.ifi.service.engineer.EngineerService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/resources/engineers")
public class EngineerAPI {
    @Inject
    EngineerService engineerService;

    @POST
    public EngineerDTO addNewEngineer(@Valid EngineerDTO engineerDTO) {
        try {
            return engineerService.addNewEngineer(engineerDTO);
        } catch (EmployeeSaveException e) {
            e.printStackTrace();
        }
        return null;
    }
}
