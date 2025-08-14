package br.iyk.metodosRequisicao;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import io.restassured.http.ContentType;

public class MetodosDeRequisicao {
	@Test
	public void validaMetodoGet() {
		// Valida o primeiro nível do JSON retornado
		given()
		.when()
			.get("http://restapi.wcaquino.me/users/1")
		.then()
			.statusCode(200) // Verifica se o código de status é 200 (Sucesso)
			.body("id", is(1)) // Verifica se o campo "id" é igual a 1
			.body("name", containsString("João")) // Verifica se o campo "name" contém "João"
			.body("age", greaterThan(18))  // Verifica se o campo "age" é maior que 18
			; 
	}
	
    @Test
    public void validaMetodoPost() {
        given()
            .contentType(ContentType.JSON) // Tipo de conteúdo da requisição
            .body("{\"name\": \"Joaozinho\",\"age\": 26}") // Corpo da requisição com os dados do usuário
        .when()
            .post("http://restapi.wcaquino.me/users") // Envia a requisição POST para o endpoint especificado
        .then()
            .log().all() // Log de todas as informações da resposta
             .statusCode(201) // Verifica se o código de status da resposta é 201 (Created)
             .body("name", is("Joaozinho")) // Verifica se o campo "name" da resposta é "Joaozinho"
             ; 
    }
    
    @Test
    public void validaMetodoDelete() {
        given()
            .log().all() // Log de todas as informações da requisição
        .when()
            .delete("http://restapi.wcaquino.me/users/1") // Envia a requisição DELETE para o endpoint especificado
        .then()
            .log().all() // Log de todas as informações da resposta
             .statusCode(204); // Verifica se o código de status da resposta é 204 (No Content)
    }
    
    @Test
    public void validaMetodoPut() {
        given()
            .contentType(ContentType.JSON) // Tipo de conteúdo da requisição
            .body("{\"name\": \"Usuario Alterado\",\"age\": 80}") // Corpo da requisição com os dados do usuário
            .pathParam("entidade", "users") //adicionado uma entidade como parametro para ser substituido na url
            .pathParam("userId", "1") //adicionado uma entidade como parametro para ser substituido na url
        .when()
            .put("http://restapi.wcaquino.me/{entidade}/{userId}") // Envia a requisição PUT com url customizada
        .then()
            .log().all() // Log de todas as informações da resposta
            .statusCode(200)
            ; // Verifica se o código de status da resposta é 200
    }
}
