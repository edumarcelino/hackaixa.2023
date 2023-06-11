package br.gov.caixa.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CustomExceptionHandler implements ExceptionMapper<CustomException> {

    String simulationNotFound = "Financiamento não encontrado";

    @Override
    public Response toResponse(CustomException e) {

        if (e.getMessage().equalsIgnoreCase(simulationNotFound)) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage(e.getMessage(), false)).build();
        } else {

            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessage(e.getMessage(), false)).build();
        }
    }
}
