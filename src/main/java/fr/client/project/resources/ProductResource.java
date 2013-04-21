package fr.client.project.resources;

import fr.client.project.entity.Product;
import fr.client.project.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/products")
public class ProductResource {

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
    return repository.findOne(id);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void create(Product product) {
    repository.save(product);
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
