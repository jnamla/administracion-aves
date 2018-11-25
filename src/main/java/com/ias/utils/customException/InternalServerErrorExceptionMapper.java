package com.ias.utils.customException;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *  Una {@link ExceptionMapper} implementacion de {@link InternalServerErrorException} para toda excepcion de negocio
 * 
 * @author jnamla
 */
@Provider
public class InternalServerErrorExceptionMapper implements ExceptionMapper<InternalServerErrorException>{
    
    @Override
    public Response toResponse(InternalServerErrorException exception) {
        
        Map<String, String> response = new HashMap<>();
            response.put("Code", "BUSINNES-ERR-GENERAL");
            response.put("Type", "LOGIC");
            response.put("Message", exception.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(response).type(MediaType.APPLICATION_JSON).build();
        
    }
}
