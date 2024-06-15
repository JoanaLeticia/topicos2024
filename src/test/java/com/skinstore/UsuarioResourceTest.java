package com.skinstore;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import com.skinstore.dto.LoginDTO;
import com.skinstore.dto.UsuarioDTO;
import com.skinstore.dto.UsuarioResponseDTO;
import com.skinstore.service.UsuarioService;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class UsuarioResourceTest {
    @Inject
    UsuarioService UsuarioService;

    private String token;
    @BeforeEach
    public void setUp() {
        var auth = new LoginDTO("victor@unitins.br", "123");

        Response response = (Response) given()
                .contentType("application/json")
                .body(auth)
                .when().post("/auth")
                .then()
                .statusCode(200)
                .extract().response();

        token = response.header("Authorization");
    }
    @Test
    public void testFindAll() {
        given()
        .header("Authorization", "Bearer " + token)
                .when().get("/usuarios")
                .then()
                .statusCode(200);
    }

    @Test
    public void testInsert() {
        UsuarioDTO dtoUsuario = new UsuarioDTO("teste", "teste@teste", 1);

        given()
        .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(dtoUsuario)
                .when().post("/usuarios")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "login", is("teste@teste"));
    }

    @Test
    public void testUpdate() {
        UsuarioDTO dtoUsuario = new UsuarioDTO("teste", "teste@teste", 1);

        // inserindo um usuario
        UsuarioResponseDTO usuarioTest = UsuarioService.insert(dtoUsuario);
        Long id = usuarioTest.id();

        UsuarioDTO dtoUpdate = new UsuarioDTO("teste2", "teste@teste2", 2);

        given()
        .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(dtoUpdate)
                .when().put("/usuarios/" + id)
                .then()
                .statusCode(204);

    }

    @Test
    public void testRemoveUsuario() {
        UsuarioDTO dtoUsuario = new UsuarioDTO("teste", "teste@teste", 1);

        UsuarioResponseDTO UsuarioInserido = UsuarioService.insert(dtoUsuario);
        Long idUsuario = UsuarioInserido.id();

        given()
        .header("Authorization", "Bearer " + token)
                .when()
                .delete("/usuarios/" + idUsuario)
                .then()
                .statusCode(204); 

    }

    @Test
    public void testFindById() {
        given()
        .header("Authorization", "Bearer " + token)
                .when().get("/usuarios/{id}", 1L)
                .then()
                .statusCode(200)
                .body("id", equalTo(1L));
    }

}