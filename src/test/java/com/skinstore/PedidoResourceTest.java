package com.skinstore;

import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import com.skinstore.dto.ItemPedidoDTO;
import com.skinstore.dto.PedidoDTO;
import com.skinstore.dto.PedidoResponseDTO;
import com.skinstore.service.PedidoService;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PedidoResourceTest {
    @Inject
    public PedidoService pedidoService;

    @Test
    public void findAllTest() {
        given()
                .when()
                .get("/pedidos")
                .then()
                .statusCode(200);
    }

    @Test
    public void findByIdTest() {
        given()
                .when()
                .get("/pedidos/1")
                .then()
                .statusCode(200)
                .body("id", is(1));
    }

    @Test
    public void findByNomeTest() {
        given()
                .when()
                .get("/pedidos/search/nome/AK-47")
                .then()
                .statusCode(200)
                .body("nome", everyItem(is("AK-47 | Neon Rider")));
    }

    /*
     * @Test
     * public void createTest() {
     * ProdutoDTO dto = new ProdutoDTO("M4A1-S | Hot Rod",
     * "https://steamcommunity.com/market/listings/730/M4A1-S%20%7C%20Hot%20Rod%20%28Factory%20New%29",
     * BigDecimal.valueOf(5444.48), Integer.valueOf(1), Integer.valueOf(4),
     * Integer.valueOf(3), Integer.valueOf(1), 0.0003295f, 333, Integer.valueOf(1));
     * 
     * given()
     * .contentType(MediaType.APPLICATION_JSON)
     * .body(dto)
     * .when()
     * .post("/produtos")
     * .then()
     * .statusCode(201)
     * .body("nome", is("M4A1-S | Hot Rod"))
     * .body("linkSteam", is(
     * "https://steamcommunity.com/market/listings/730/M4A1-S%20%7C%20Hot%20Rod%20%28Factory%20New%29"
     * ))
     * .body("valor", equalTo((float) 5444.48))
     * .body("numeroFloat", is(0.0003295f))
     * .body("pattern", is(333));
     * }
     */

    @Test
    public void updateTest() {
        List<ItemPedidoDTO> listaItensPedidos = new ArrayList<ItemPedidoDTO>();
        listaItensPedidos.add(new ItemPedidoDTO(Integer.valueOf(1), 40.0, 1L));

        PedidoDTO dto = new PedidoDTO(Long.valueOf(1), listaItensPedidos);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .when()
                .pathParam("id", 1)
                .put("/pedidos/{id}")
                .then()
                .statusCode(204);
    }

    @Test
    public void deleteTest() {
        List<ItemPedidoDTO> listaItensPedidos = new ArrayList<ItemPedidoDTO>();

        listaItensPedidos.add(new ItemPedidoDTO(Integer.valueOf(1), 523.03, 2L));

        PedidoDTO dto = new PedidoDTO(Long.valueOf(1), listaItensPedidos);

        PedidoResponseDTO response = pedidoService.insert(dto, null);

        given()
                .when()
                .pathParam("id", response.id())
                .delete("/pedidos/{id}")
                .then()
                .statusCode(204);

        pedidoService.delete(response.id());
        assertNull(pedidoService.findById(response.id()));
    }
}
