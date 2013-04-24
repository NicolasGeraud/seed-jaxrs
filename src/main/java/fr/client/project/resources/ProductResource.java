package fr.client.project.resources;

import fr.client.project.entity.Product;
import fr.client.project.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Component
@Path("/products")
public class ProductResource extends AbstractRESTService {

  @Autowired
  protected ProductsRepository repository;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Product> findAll() {
    return repository.findAll();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Product findById(@PathParam("id") Long id) {
    Product product = repository.findOne(id);
    notFoundIfNull(product);
    return product;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(Product product) throws URISyntaxException {
    repository.save(product);
    return Response.created(new URI(product.getId().toString())).build();
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public void update(@PathParam("id") Long id, Product product) {
    product.setId(id);
    repository.save(product);
  }

  @DELETE
  @Path("/{id}")
  public void delete(@PathParam("id") Long id) {
    repository.delete(id);
  }

}
