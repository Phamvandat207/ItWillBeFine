package com.ifi.rest;

import com.ifi.data.exception.EmployeeDataException;
import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.dto.WrapperDTO;
import com.ifi.service.EmployeeServiceFacade;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employees")
public class EmployeeFacadeAPI {
    @Inject
    EmployeeServiceFacade employeeServiceFacade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(
                employeeServiceFacade.findAll()
        ).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(WrapperDTO wrapperDTO) {
        try {
            return Response.ok(
                    employeeServiceFacade.addEmployee(wrapperDTO.getEmployeeDTO())
            ).build();
        } catch (EmployeeSaveException e) {
            return Response.status(400, "Cannot add employee with pre-defined id").build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(WrapperDTO wrapperDTO) {
        try {
            return Response.ok(
                    employeeServiceFacade.updateEmployee(wrapperDTO.getEmployeeDTO())
            ).build();
        } catch (EmployeeSaveException e) {
            return Response.status(400, "save employee error").build();
        } catch (EmployeeDataException e) {
            return Response.status(400, "id null error").build();
        }
    }
}
