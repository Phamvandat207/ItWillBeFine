package com.ifi.rest;

import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.dto.EngineerDTO;
import com.ifi.service.engineer.EngineerService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/engineers")
public class EngineerAPI {
    @Inject
    EngineerService engineerService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewEngineer(@Valid EngineerDTO engineerDTO) {
        EngineerDTO engineerAdded;
        try {
            engineerAdded = engineerService.addNewEngineer(engineerDTO);
        } catch (EmployeeSaveException e) {
            return Response.status(400, "error saved entity").build();
        }
        return Response.ok(engineerAdded).build();
    }
}
