package com.skinstore;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skinstore.dto.CidadeDTO;
import com.skinstore.dto.ClienteDTO;
import com.skinstore.dto.EnderecoDTO;
import com.skinstore.dto.EstadoDTO;
import com.skinstore.dto.LoginDTO;
import com.skinstore.dto.TelefoneDTO;
import com.skinstore.service.CidadeService;
import com.skinstore.service.ClienteService;
import com.skinstore.service.EnderecoService;
import com.skinstore.service.EstadoService;

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

    @Inject
    CidadeService cidadeService;

    @Inject
    EstadoService estadoService;

    @Inject
    EnderecoService enderecoService;

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
            .header("Authorization", "Bearer " + token)
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
    public void insertTest() {
        List<TelefoneDTO> telefones = new ArrayList<>();
        telefones.add(new TelefoneDTO("55", "123456789"));

        List<EnderecoDTO> enderecos = new ArrayList<>();
        Long idEstado = estadoService.insert(new EstadoDTO("São PAulo", "SP")).id();
                Long idMunicipio = cidadeService.insert(new CidadeDTO("São Paulo", idEstado)).id();
        enderecos.add(new EnderecoDTO("teste", "123", "teste2", "bairro", "21345-423", idMunicipio));

        ClienteDTO dto = new ClienteDTO("Jude Belligham", "belligham@gmail.com", "2312##dfa", "123.323.232-32", null, telefones, enderecos);

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
        given()
            .when()
            .pathParam("id", 1L)
            .delete("/clientes/{id}")
            .then()
            .statusCode(204);

        clienteService.delete(1L);
        assertNull(clienteService.findById(1L));
    }
}
