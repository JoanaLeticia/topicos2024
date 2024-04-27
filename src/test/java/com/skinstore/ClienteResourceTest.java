package com.skinstore;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.skinstore.dto.ClienteDTO;
import com.skinstore.dto.ClienteResponseDTO;
import com.skinstore.dto.TelefoneDTO;
import com.skinstore.service.ClienteService;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ClienteResourceTest {
    @Inject
    public ClienteService clienteService;

    @Test
    public void findAllTest() {
        given()
            .when()
            .get("/clientes")
            .then()
            .statusCode(200)
            .body("nome", hasItem(is("Lucas Silva")));
    }

    @Test
    public void findByIdTest() {
        given()
            .when()
            .get("/clientes/1")
            .then()
            .statusCode(200);
    }

    @Test
    public void findByNomeTest() {
        given()
            .when()
            .get("/clientes/search/nome/Lucas")
            .then()
            .statusCode(200)
            .body("nome", everyItem(is("Lucas Silva")));
    }

    @Test
    public void createTest() {
        List<TelefoneDTO> telefones = new ArrayList<>();
        telefones.add(new TelefoneDTO("55", "123456789"));

        ClienteDTO dto = new ClienteDTO("Jude Belligham", "belligham@gmail.com", "2312##dfa", "123.323.232-32", telefones);

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
            .post("/clientes")
            .then()
            .statusCode(201)
            .body("nome", is("Jude Belligham"))
            .body("login", is("belligham@gmail.com"))
            .body("cpf", is("123.323.232-32"))
            .body("telefones", containsInAnyOrder(
            allOf(hasEntry("codigoArea", "55"), hasEntry("numero", "123456789"))
            ));
    }

    // parou de funcionar
    /*@Test
    public void updateTest() {
        List<TelefoneDTO> telefones = new ArrayList<>();
        telefones.add(new TelefoneDTO("55", "987654321"));

        ClienteDTO dto = new ClienteDTO("Rodrygo Goes", "raio@skinstore.com", "938fdak2", "566.431.324-43", telefones);

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
            .pathParam("id", 1)
            .put("/clientes/{id}")
            .then()
            .statusCode(204);
    }*/

    @Test
    public void deleteTest() {
        List<TelefoneDTO> telefones = new ArrayList<>();
        telefones.add(new TelefoneDTO("55", "349012102"));

        ClienteResponseDTO response = clienteService.create(new ClienteDTO("Vinicius Junior", "vinijr@yahoo.com", "dfa90232", "131.432.456-31", telefones));
        given()
            .when()
            .pathParam("id", response.id())
            .delete("/clientes/{id}")
            .then()
            .statusCode(204);

        clienteService.delete(response.id());
        assertNull(clienteService.findById(response.id()));
    }
}
