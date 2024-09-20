package org.ritpl.exception.handler;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.ritpl.exception.dto.ErrorDTO;
import org.ritpl.exception.errormodel.CarNotFoundException;

import java.time.LocalDateTime;


@Provider
public class GlobalExceptionCarNotFound implements ExceptionMapper<CarNotFoundException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(CarNotFoundException exception) {
        ErrorDTO error = new ErrorDTO(
                LocalDateTime.now(),
                Response.Status.NOT_FOUND.getStatusCode(),
                exception.getMessage(),
                uriInfo.getPath());

        return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }
}
