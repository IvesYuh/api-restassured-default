package br.iyk.metodosAutenticacao;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import br.iyk.core.BaseTest;
import br.iyk.core.BaseToken;
import br.iyk.entidades.DadosMetodoAutenticacao;

public class MetodoAutenticacao extends BaseTest{
	private static final String TOKEN_INSTALL = DadosMetodoAutenticacao.retornaTokenInstall();

	@Test
	public void validaMetodoAutenticacaoTokenInstall() {
		Map<String, String> body = new HashMap<>(); // Criação do Json para envio no BODY
        body.put("login", "RESTASSURED");
        body.put("senha", "e10adc3949ba59abbe56e057f20f883e");
        body.put("acessToken", TOKEN_INSTALL);
        
            	given()
            		.header("Authorization", TOKEN_INSTALL) // Setando o token no Authorization
            		.body(body) //Incluido o body criado
                .when()
                    .post("/install") // Requisição POST na url 
                .then()
                	.statusCode(200) // Validação do statusCode
                	;
	}
	
	@Test
	public void validaMetodoAutenticacaoToken() {
		Map<String, String> body = new HashMap<>();  // Criação do Json para envio no BODY
        body.put("login", "RESTASSURED");
        body.put("senha", "e10adc3949ba59abbe56e057f20f883e");
        
        	given()
                .header("Authorization", BaseToken.retornaTokenInstall())   // Setando o token no Authorization
                .body(body) //Incluido o body criado
            .when()
                .post("/login")   // Requisição POST na url 
            .then()
                .statusCode(200) // Validação do statusCode
                ;
	}
	
	@Test
	public void validaBareerToken() {
		DadosMetodoAutenticacao dados = new DadosMetodoAutenticacao("32282", "1F0D24F7-87F6-4895-85EB-5D33CFD08D0E");
			given()
				.auth().oauth2(BaseToken.retornaTokenManual()) // Inserindo o Token na Autorização (Bareer Token)
				.pathParam("idGuia", dados.getIdGuia()) //Setando o idGuia no parametro idGuia
				.pathParam("keyGuia", dados.getKeyGuia()) //Setando o keyGuia no parametro keyGuia
			.when()
				.get("/v1/guia/{idGuia}/anexo?appKey={keyGuia}")
			.then()
				.statusCode(200) // Validação do statusCode
				;
	}	
}
