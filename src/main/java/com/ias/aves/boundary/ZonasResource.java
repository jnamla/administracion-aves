/*
 * This class is the ReST implementation for the Asset. 
 */
package com.ias.aves.boundary;

import com.ias.aves.domain.Zona;
import java.util.Collection;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ias.aves.domain.service.ZonasService;

/**
 * Implementacion REST para zonas
 * 
 * @author jnamla
 */

@Path("zonas")
@RequestScoped
public class ZonasResource {
    
    @Inject
    private ZonasService zonasService;
   
    
    @GET
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public Response aves() {
        Collection<Zona> zonas = zonasService.findAll();
        return Response.ok(zonas).build();
    }
}
