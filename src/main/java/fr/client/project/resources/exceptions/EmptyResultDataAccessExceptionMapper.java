package fr.client.project.resources.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EmptyResultDataAccessExceptionMapper implements ExceptionMapper<EmptyResultDataAccessException> {

  public Response toResponse(EmptyResultDataAccessException exception) {
    return new NotFoundException().getResponse();
  }
}
