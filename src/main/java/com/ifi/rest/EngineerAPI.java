package com.ifi.rest;

import com.ifi.data.exception.EmployeeDataException;
import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.dto.EngineerDTO;
import com.ifi.service.engineer.EngineerService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
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

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEngineer(@Valid EngineerDTO engineerDTO) {
        EngineerDTO engineerUpdated;
        try {
            engineerUpdated = engineerService.updateEngineer(engineerDTO);
        } catch (EmployeeSaveException e) {
            return Response.status(400, "cant save engineer").build();
        } catch (EmployeeDataException e) {
            return Response.status(400, "engineer id cannot null").build();
        }
        return Response.ok(engineerUpdated).build();
    }
}
