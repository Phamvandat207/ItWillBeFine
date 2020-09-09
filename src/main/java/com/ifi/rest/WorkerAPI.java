package com.ifi.rest;

import com.ifi.data.exception.EmployeeDataException;
import com.ifi.data.exception.EmployeeSaveException;
import com.ifi.dto.WorkerDTO;
import com.ifi.service.exception.DeleteEntityException;
import com.ifi.service.worker.WorkerService;
import com.ifi.util.message.ResponseMessage;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/workers")
public class WorkerAPI {
    @Inject
    WorkerService workerService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewWorker(@Valid WorkerDTO workerDTO) {
        WorkerDTO addedWorker;
        try {
            addedWorker = workerService.addWorker(workerDTO);
        } catch (EmployeeSaveException e) {
            return Response.status(400, "error add worker").build();
        }
        return Response.ok(addedWorker).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateWorker(@Valid WorkerDTO workerDTO) {
        WorkerDTO updatedWorker;
        try {
            updatedWorker = workerService.updateWorker(workerDTO);
        } catch (EmployeeSaveException e) {
            return Response.status(400, "cant update worker").build();
        } catch (EmployeeDataException e) {
            return Response.status(400, "worker id cannot be null").build();
        }
        return Response.ok(updatedWorker).build();
    }

    @DELETE
    @Path("/{workerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteWorker(@PathParam("workerId") String id) {
        try {
            workerService.deleteWorker(id);
        } catch (DeleteEntityException e) {
            return Response.status(400, "error delete worker").build();
        }
        return Response.ok(new ResponseMessage(200, "worker deleted")).build();
    }
}
