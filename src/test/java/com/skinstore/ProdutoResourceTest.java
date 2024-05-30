package com.skinstore;

import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import com.skinstore.dto.ProdutoDTO;
import com.skinstore.dto.ProdutoResponseDTO;
import com.skinstore.service.ProdutoService;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ProdutoResourceTest {
    @Inject
    public ProdutoService produtoService;

    @Test
    public void findAllTest() {
        given()
            .when()
            .get("/produtos")
            .then()
            .statusCode(200);
    }

    @Test
    public void findByIdTest() {
        given()
            .when()
            .get("/produtos/1")
            .then()
            .statusCode(200)
            .body("id", is(1));
    }

    @Test
    public void findByNomeTest() {
        given()
            .when()
            .get("/produtos/search/nome/AK-47")
            .then()
            .statusCode(200)
            .body("nome", everyItem(is("AK-47 | Neon Rider")));
    }

/*  @Test
    public void createTest() {
        ProdutoDTO dto = new ProdutoDTO("M4A1-S | Hot Rod", "https://steamcommunity.com/market/listings/730/M4A1-S%20%7C%20Hot%20Rod%20%28Factory%20New%29", BigDecimal.valueOf(5444.48), Integer.valueOf(1), Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), 0.0003295f, 333, Integer.valueOf(1));

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
            .post("/produtos")
            .then()
            .statusCode(201)
            .body("nome", is("M4A1-S | Hot Rod"))
            .body("linkSteam", is("https://steamcommunity.com/market/listings/730/M4A1-S%20%7C%20Hot%20Rod%20%28Factory%20New%29"))
            .body("valor", equalTo((float) 5444.48))
            .body("numeroFloat", is(0.0003295f))
            .body("pattern", is(333));
    }*/

    @Test
    public void updateTest() {
        ProdutoDTO dto = new ProdutoDTO("StatTrak™ M4A4 | The Emperor", "https://steamcommunity.com/market/listings/730/StatTrak%E2%84%A2%20M4A4%20%7C%20The%20Emperor%20%28Factory%20New%29", BigDecimal.valueOf(2915.95), Integer.valueOf(1), Integer.valueOf(4), Integer.valueOf(2), Integer.valueOf(1), 0.0003295f, 333, Integer.valueOf(6));

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
            .pathParam("id", 1)
            .put("/produtos/{id}")
            .then()
            .statusCode(204);
    }

    @Test
    public void deleteTest() {
        ProdutoDTO dto = new ProdutoDTO("MP9 | Pandora's Box", "https://steamcommunity.com/market/listings/730/MP9%20%7C%20Pandora%27s%20Box%20%28Field-Tested%29", BigDecimal.valueOf(717.71), Integer.valueOf(1), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(3), 0.032142f, 112, Integer.valueOf(1));

        ProdutoResponseDTO response = produtoService.create(dto);

        given()
            .when()
            .pathParam("id", response.id())
            .delete("/produtos/{id}")
            .then()
            .statusCode(204);

        produtoService.delete(response.id());
        assertNull(produtoService.findById(response.id()));
    }
}
