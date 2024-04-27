package com.skinstore;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.skinstore.dto.EstadoDTO;
import com.skinstore.dto.EstadoResponseDTO;
import com.skinstore.service.EstadoService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
public class EstadoResourceTest {
    @Inject
    public EstadoService estadoService;

    @Test
    public void findAllTest() {
        given()
            .when()
            .get("/estados")
            .then()
            .statusCode(200)
            .body("nome", hasItem(is("Maranhão")));
    }

    @Test
    public void findByIdTest() {
        given()
            .when()
            .get("/estados/2")
            .then()
            .statusCode(200)
            .body("id", is(2));
    }

    @Test
    public void findByNomeTest() {
        given()
            .when()
            .get("/estados/search/nome/Maranhão")
            .then()
            .statusCode(200)
            .body("nome", everyItem(is("Maranhão")));
    }

    @Test
    public void findBySiglaTest() {
        given()
            .when()
            .get("/estados/search/sigla/MA")
            .then()
            .statusCode(200)
            .body("sigla", everyItem(is("MA")));
    }

    @Test
    public void createTest() {
        EstadoDTO dto = new EstadoDTO("São Paulo", "SP");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
            .post("/estados")
            .then()
            .statusCode(201)
            .body("nome", is("São Paulo"))
            .body("sigla", is("SP"));
    }

    @Test
    public void updateTest() {
        EstadoDTO dto = new EstadoDTO("Itapevi", "SP");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
            .pathParam("id", 3)
            .put("/estados/{id}")
            .then()
            .statusCode(204);
    }

    @Test
    public void deleteTest() {
        EstadoResponseDTO response = estadoService.create(new EstadoDTO("Rio de Janeiro", "RJ"));
        given()
            .when()
            .pathParam("id", response.id())
            .delete("/estados/{id}")
            .then()
            .statusCode(204);

        estadoService.delete(response.id());
        assertNull(estadoService.findById(response.id()));
    }

}
