package org.ritpl.exception.handler;


import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.ritpl.exception.dto.ErrorDTO;
import org.ritpl.exception.errormodel.CarListIsEmptyException;

import java.time.LocalDateTime;

@Provider
public class GlobalExceptionCarListIsEmpty implements ExceptionMapper<CarListIsEmptyException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(CarListIsEmptyException exception) {
        // Build custom error response
        ErrorDTO error = new ErrorDTO(
                LocalDateTime.now(),
                Response.Status.OK.getStatusCode(),
                exception.getMessage(),
                uriInfo.getPath());

        return Response.status(Response.Status.OK)
                .entity(error)
                .build();
    }

}
