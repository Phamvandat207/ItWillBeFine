package com.ifi.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/notification")
public class Notification {
    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Hello World").build();
    }
}
