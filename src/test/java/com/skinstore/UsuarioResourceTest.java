package com.skinstore;

import org.junit.jupiter.api.Test;

import com.skinstore.dto.UsuarioDTO;
import com.skinstore.dto.UsuarioResponseDTO;
import com.skinstore.service.UsuarioService;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UsuarioResourceTest {
    @Inject
    public UsuarioService usuarioService;

    @Test
    public void findAllTest() {
        given()
            .when()
            .get("/usuarios")
            .then()
            .statusCode(200);
    }

    @Test
    public void findByIdTest() {
        given()
            .when()
            .get("/usuarios/1")
            .then()
            .statusCode(200)
            .body("id", is(1));
    }

    @Test
    public void findByLoginTest() {
        given()
            .when()
            .get("/usuarios/search/login/luis")
            .then()
            .statusCode(200)
            .body("login", everyItem(is("luisvictor@unitins.br")));
    }

    @Test
    public void createTest() {
        UsuarioDTO dto = new UsuarioDTO("Jordan Sanchez", "jjs@gmail.com", "dfsa2d34sd3");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
            .post("/usuarios")
            .then()
            .statusCode(201);
    }

    @Test
    public void updateTest() {
        UsuarioDTO dto = new UsuarioDTO("Linda Wendy", "wendys@gmail.com", "dfsd343");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
            .pathParam("id", 1)
            .put("/usuarios/{id}")
            .then()
            .statusCode(204);
    }

    @Test
    public void deleteTest() {
        UsuarioDTO dto = new UsuarioDTO("Natanzinho", "natan@yahoo.com", "12343jj");

        UsuarioResponseDTO UsuarioInserido = usuarioService.create(dto);
        Long idUsuario = UsuarioInserido.id();
        given()
            .when()
            .delete("/usuarios/" + idUsuario)
            .then()
            .statusCode(204);
    }
}
