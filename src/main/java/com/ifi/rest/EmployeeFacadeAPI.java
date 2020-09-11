package com.ifi.rest;

import com.ifi.data.exception.EmployeeDataException;
import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.dto.EmployeeDTO;
import com.ifi.service.EmployeeServiceFacade;
import com.ifi.service.exception.DeleteEntityException;
import com.ifi.util.message.ResponseMessage;

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
    public Response create(@Valid EmployeeDTO employeeDTO) {
        try {
            return Response.ok(
                    employeeServiceFacade.addEmployee(employeeDTO)
            ).build();
        } catch (EmployeeSaveException e) {
            return Response.status(400, "Cannot add employee with pre-defined id").build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid EmployeeDTO employeeDTO) {
        try {
            return Response.ok(
                    employeeServiceFacade.updateEmployee(employeeDTO)
            ).build();
        } catch (EmployeeSaveException e) {
            return Response.status(400, "save employee error").build();
        } catch (EmployeeDataException e) {
            return Response.status(400, "id null error").build();
        }
    }

    @DELETE
    @Path("/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(@PathParam("employeeId") String id) {
        try {
            employeeServiceFacade.deleteEmployee(id);
        } catch (DeleteEntityException e) {
            return Response.status(400, "error delete employee").build();
        }
        return Response.ok(new ResponseMessage(200, "employee deleted")).build();
    }
}
