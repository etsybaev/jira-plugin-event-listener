package com.aval.jira.plugins.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * A resource of message.
 */
@Path("/message")
public class BaseRestController {

    @GET
//    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public Response getMessage()
    {
       return Response.ok(new BaseRestControllerModel("Hello World from rest controller")).build();
    }
}