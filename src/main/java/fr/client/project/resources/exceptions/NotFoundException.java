package fr.client.project.resources.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class NotFoundException extends WebApplicationException {

  public NotFoundException() {
    // The content length is kind of a hack but it forces Tomcat and others
    // to respond with empty body
    super(Response.status(404).header("Content-Length", 0).build());
  }
}
