package com.skinstore;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skinstore.dto.LoginDTO;
import com.skinstore.dto.TelefoneDTO;
import com.skinstore.dto.TelefoneResponseDTO;
import com.skinstore.service.TelefoneService;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class TelefoneResourceTest {
    @Inject
    TelefoneService administradorService;
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
                .when().get("/administradores")
                .then()
                .statusCode(200);
    }

    @Test
    public void testInsert() {
        TelefoneDTO dtoTelefone = new TelefoneDTO(
                "63",
                "984398131");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(dtoTelefone)
                .when().post("/telefones")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "codigoArea", is("63"),
                        "numero", is("984398131"));
    }

    @Test
    public void testUpdate() {
        TelefoneDTO dtoUpdate = new TelefoneDTO(
                "62",
                "984398131");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(dtoUpdate)
                .when().put("/telefones/" + 2L)
                .then()
                .statusCode(204);

        TelefoneResponseDTO usu = administradorService.findById(2L);
        assertThat(usu.codigoArea(), is("62"));
        assertThat(usu.numero(), is("984398131"));

    }

    @Test
    public void testRemoveTelefone() {
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/felefones/" + 1L)
                .then()
                .statusCode(404);
    }

    @Test
    public void testFindById() {
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/telefones/{id}", 1L)
                .then()
                .statusCode(200)
                .body("id", equalTo(1L));
    }

    @Test
    public void testFindByIdNotFound() {
        Long idNaoExistente = 9999L;

        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/administradores/{id}", idNaoExistente)
                .then()
                .statusCode(404);
    }

    @Test
    public void testFindByNome() {
        String numeroExistente = "98478-3692";

        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/telefones/search/numero/{numero}", numeroExistente)
                .then()
                .statusCode(200)
                .body("numero[0]", equalTo(numeroExistente));
    }

    @Test
    public void testFindByNomeNotFound() {
        String numeroNaoExistente = "00000-888";

        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/telefones/search/numero/{numero}", numeroNaoExistente)
                .then()
                .statusCode(200)
                .body("$", hasSize(equalTo(0)));
    }

}
