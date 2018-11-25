/*
 *  Sub REST resource implementation for areas. 
 */
package com.ias.aves.boundary;

import com.ias.aves.domain.AvePais;
import java.util.Collection;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ias.aves.domain.service.AvePaisService;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Implementacion REST de AvesPais
 * 
 * @author jnamla
 */
@RequestScoped
public class AvesPaisResource {
    
    @Inject
    private AvePaisService avePaisService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public Response avesPaisAll() {
        Collection<AvePais> aves = avePaisService.findAll();
        return Response.ok(aves).build();
    }
    
    @GET
    @Path("/nombre/{nombre}/zona/{zona}")
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public Response avesPaisByNombreZona(@PathParam("nombre") String nombre, @PathParam("zona") String zona) {
        Collection<AvePais> aves = avePaisService.findByNameAndZone(nombre, zona);
        return Response.ok(aves).build();
    }
    
    @GET
    @Path("/nombre/{nombre}")
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public Response avesPaisByNombreAve(@PathParam("nombre") String nombre) {
        Collection<AvePais> aves = avePaisService.findByNombreAve(nombre);
        return Response.ok(aves).build();
    }
    
    @GET
    @Path("/zona/{zona}")
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public Response avesPaisByZone(@PathParam("zona") String zona) {
        Collection<AvePais> aves = avePaisService.findByZona(zona);
        return Response.ok(aves).build();
    }
}
