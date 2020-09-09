package com.ifi.rest;

import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.dto.EmployeeDTO;
import com.ifi.service.employee.EmployeeService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/resources/employees")
public class EmployeeRest {
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
}
