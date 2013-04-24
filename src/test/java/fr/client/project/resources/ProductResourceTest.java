package fr.client.project.resources;

import com.jayway.restassured.RestAssured;
import fr.client.project.entity.Product;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ProductResourceTest {

  private Product product = null;

  @Before
  public void before() {
    RestAssured.baseURI = "http://localhost:8080/api";

    product = new Product();
    product.setName("A new awesome product!");
    product.setPrice(100.01);
    product.setReference("test-42");
    product.setCategory("A new category");
  }

  @Test
  public void getProductTest() {
    expect()
        .statusCode(200)
        .body("name", equalTo("fruit juice"))
    .when()
        .get("/products/1");

    expect()
        .statusCode(404)
    .when()
        .get("/products/10000");
  }

  @Test
  public void postProductTest() {
    String location = given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(product)
    .expect()
        .statusCode(201)
    .when()
        .post("/products")
        .getHeader("Location");

    expect()
        .statusCode(200)
        .body("name", equalTo(product.getName()))
        .body("price", equalTo(product.getPrice()))
        .body("reference", equalTo(product.getReference()))
        .body("category", equalTo(product.getCategory()))
    .when()
        .get(location);
  }

  @Test
  public void updateProductTest() {
    given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(product)
    .expect()
        .statusCode(204)
    .when()
        .put("/products/1");

    expect()
        .statusCode(200)
        .body("name", equalTo(product.getName()))
        .body("price", equalTo(product.getPrice()))
        .body("reference", equalTo(product.getReference()))
        .body("category", equalTo(product.getCategory()))
    .when()
        .get("/products/1");
  }

  @Test
  public void deleteProductTest() {
    String location = given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(product)
    .expect()
        .statusCode(201)
    .when()
        .post("/products")
        .getHeader("Location");

    expect()
        .statusCode(204)
    .when()
        .delete(location);

    expect()
        .statusCode(404)
    .when()
        .delete(location);
  }

}
