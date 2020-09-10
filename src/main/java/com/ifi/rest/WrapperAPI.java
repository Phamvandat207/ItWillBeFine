package com.ifi.rest;

import com.ifi.dto.WrapperDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/wrapper")
public class WrapperAPI {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public WrapperDTO postDTO(WrapperDTO wrapperDTO) {
        System.out.println(wrapperDTO);
        System.out.println();
        return wrapperDTO;
    }
}
