package com.ifi.rest;

import com.ifi.data.exception.EmployeeDataException;
import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.dto.EmployeeDTO;
import com.ifi.service.employee.EmployeeService;
import com.ifi.service.exception.DeleteEntityException;
import com.ifi.util.message.ResponseMessage;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employees-old")
public class EmployeeAPI {
    @Inject
    EmployeeService employeeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeList() {
        List<EmployeeDTO> employees = employeeService.findAllEmployee();
        return Response.ok(employees).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewEmployee(@Valid EmployeeDTO employeeDTO) {
        EmployeeDTO employeeAdded;
        try {
            employeeAdded = employeeService.createNewEmployee(employeeDTO);
        } catch (EmployeeSaveException e) {
            return Response.status(400, "Cannot add employee with pre-defined id").build();
        }
        return Response.ok(employeeAdded).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editEmployee(@Valid EmployeeDTO employeeDTO) {
        EmployeeDTO edited;
        try {
            edited = employeeService.updateEmployee(employeeDTO);
        } catch (EmployeeSaveException e) {
            return Response.status(400, "save employee error").build();
        } catch (EmployeeDataException e) {
            return Response.status(400, "id null error").build();
        }
        return Response.ok(edited).build();
    }


    @DELETE
    @Path("/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteWorker(@PathParam("employeeId") String id) {
        try {
            employeeService.deleteEmployee(id);
        } catch (DeleteEntityException e) {
            return Response.status(400, "error delete employee").build();
        }
        return Response.ok(new ResponseMessage(200, "employee deleted")).build();
    }
}
