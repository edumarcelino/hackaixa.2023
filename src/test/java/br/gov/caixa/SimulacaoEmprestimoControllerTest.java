package br.gov.caixa;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
public class SimulacaoEmprestimoControllerTest {

    @Test
    public void testSimularEmprestimo_Resultado_Prazo_Nao_Informado() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"valorDesejado\": 10000 }")
                .when()
                .post("/api/v1/financiamento/simular")
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                .contentType(ContentType.JSON)
                .body("message", equalTo("O Prazo deve ser informado."))
                .body("status", equalTo(false));
    }

    @Test
    public void testSimularEmprestimo_Resultado_Prazo_Zero() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"prazo\": 0, \"valorDesejado\": 10000 }")
                .when()
                .post("/api/v1/financiamento/simular")
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                .contentType(ContentType.JSON)
                .body("message", equalTo("O Prazo deve ser maior que zero."))
                .body("status", equalTo(false));
    }
    @Test
    public void testSimularEmprestimo_Resultado_Prazo_Negativo() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"prazo\": -1, \"valorDesejado\": 10000 }")
                .when()
                .post("/api/v1/financiamento/simular")
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                .contentType(ContentType.JSON)
                .body("message", equalTo("O Prazo deve ser maior que zero."))
                .body("status", equalTo(false));
    }

    @Test
    public void testSimularEmprestimo_Resultado_Valor_Nao_Informado() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"prazo\": 2 }")
                .when()
                .post("/api/v1/financiamento/simular")
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                .contentType(ContentType.JSON)
                .body("message", equalTo("O Valor deve ser informado."))
                .body("status", equalTo(false));
    }

    @Test
    public void testSimularEmprestimo_Resultado_Valor_Negativo() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"prazo\": 2, \"valorDesejado\": -10000 }")
                .when()
                .post("/api/v1/financiamento/simular")
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                .contentType(ContentType.JSON)
                .body("message", equalTo("O Valor deve ser maior que zero."))
                .body("status", equalTo(false));
    }

    @Test
    public void testSimularEmprestimo_Resultado_Valor_Zero() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"prazo\": 2, \"valorDesejado\": 0 }")
                .when()
                .post("/api/v1/financiamento/simular")
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                .contentType(ContentType.JSON)
                .body("message", equalTo("O Valor deve ser maior que zero."))
                .body("status", equalTo(false));
    }

    @Test
    public void testSimularEmprestimo_Resultado_Primeiro_Produto() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"prazo\": 12, \"valorDesejado\": 10000 }")
                .when()
                .post("/api/v1/financiamento/simular")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .contentType(ContentType.JSON)
                .body("[0].produto.codigoProduto", equalTo(1));
    }

    @Test
    public void testSimularEmprestimo_Resultado_Segundo_Produto() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"prazo\": 25, \"valorDesejado\": 100000 }")
                .when()
                .post("/api/v1/financiamento/simular")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .contentType(ContentType.JSON)
                .body("[0].produto.codigoProduto", equalTo(2));
    }

    @Test
    public void testSimularEmprestimo_Resultado_Terceiro_Produto() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"prazo\": 25, \"valorDesejado\": 100000 }")
                .when()
                .post("/api/v1/financiamento/simular")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .contentType(ContentType.JSON)
                .body("[0].produto.codigoProduto", equalTo(2));
    }

}