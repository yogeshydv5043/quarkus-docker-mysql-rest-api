package org.ritpl.exception.handler;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.ritpl.exception.dto.ErrorDTO;
import org.ritpl.exception.errormodel.CarDetailsIsBlankException;

import java.time.LocalDateTime;

@Provider
public class GlobalExceptionCarDetailsIsBlank implements ExceptionMapper<CarDetailsIsBlankException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(CarDetailsIsBlankException exception) {
        ErrorDTO errorDTO = new ErrorDTO(LocalDateTime.now(),
                Response.Status.BAD_REQUEST.getStatusCode(),
                exception.getMessage(),
                uriInfo.getPath());
        return Response.status(Response.Status.BAD_REQUEST).entity(errorDTO).build();
    }
}
