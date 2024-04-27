package com.skinstore;

import org.junit.jupiter.api.Test;
import com.skinstore.dto.AdministradorDTO;
import com.skinstore.service.AdministradorService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
public class AdministradorResourceTest {
    @Inject
    public AdministradorService admService;

    @Test
    public void findAllTest() {
        given()
            .when()
            .get("/admins")
            .then()
            .statusCode(200)
            .body("nome", hasItem(is("Luis Victor")));
    }

    @Test
    public void findByIdTest() {
        given()
            .when()
            .get("/admins/2")
            .then()
            .statusCode(200)
            .body("id", is(2));
    }

    @Test
    public void findByNomeTest() {
        given()
            .when()
            .get("/admins/search/nome/Luis")
            .then()
            .statusCode(200)
            .body("nome", everyItem(is("Luis Victor")));
    }

    /*@Test
    public void createTest() {
        AdministradorDTO dto = new AdministradorDTO("teste", 123, "teste@teste", "32123");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
            .post("/admins")
            .then()
            .statusCode(201);
    }*/

    @Test
    public void updateTest() {
        AdministradorDTO dto = new AdministradorDTO("Son Heung-min", 983129, "son@yahoo.com", "fda9213");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
            .pathParam("id", 1)
            .put("/admins/{id}")
            .then()
            .statusCode(204);
    }

    @Test
    public void deleteTest() {

        given()
            .when()
            .delete("/admins/2")
            .then()
            .statusCode(204);
    }
}
