/*
 * This class is the ReST implementation for the Asset. 
 */
package com.ias.aves.boundary;

import com.ias.aves.domain.Ave;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.ias.aves.domain.service.AvesService;
import javax.ws.rs.DELETE;

/**
 * Implementacion REST para Aves
 * 
 * @author jnamla
 */

@Path("aves")
@RequestScoped
public class AvesResource {
    
    @Inject
    private AvesService avesService;
    
    @Context
    private ResourceContext context;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public Response aves() {
        Collection<Ave> aves = avesService.findAll();
        return Response.ok(aves).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    @Path("/{id}")
    public Response getById(@PathParam("id") String id) {
        Ave result = avesService.findByIdAve(id);
        return Response.ok(result).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    @Path("/nombre/{nombre}")
    public Response getByName(@PathParam("nombre") String nombre) {
        Collection<Ave> aves = avesService.findByName(nombre);
        return Response.ok(aves).build();
    }
 
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Ave ave) {
        avesService.create(ave);
        URI location = UriBuilder.fromResource(AvesResource.class)
                .path("/{id}")
                .resolveTemplate("id", ave.getCdAve())
                .build();
        return Response.created(location).build();
    }
    
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, Ave ave) {
        avesService.update(id, ave);
        
        Map<String, String> response = new HashMap<>();
            response.put("Code", "PERSISTENCE-GENERAL");
            response.put("Type", "DATABASE");
            response.put("Message", "Ave actualizada");
            
        return Response.ok(response).type(MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        avesService.delete(id);
        Map<String, String> response = new HashMap<>();
            response.put("Code", "PERSISTENCE-GENERAL");
            response.put("Type", "DATABASE");
            response.put("Message", "Ave eliminada");
        return Response.ok(response).type(MediaType.APPLICATION_JSON).build();
    }
    
    @Path("/busqueda-avanzada")
    public AvesPaisResource avesPaisAll() {
        AvesPaisResource avesPaisResource = context.getResource(AvesPaisResource.class);
        return avesPaisResource;
    }
    
}
