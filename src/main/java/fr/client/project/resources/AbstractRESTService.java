package fr.client.project.resources;

import fr.client.project.resources.exceptions.NotFoundException;

public abstract class AbstractRESTService {

  public void notFoundIfNull(Object object) {
    if (object == null) {
      throw new NotFoundException();
    }
  }
}
