package br.iyk.manipulacaoArquivos;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.hamcrest.Matchers;
import org.junit.Test;

public class UploadArquivo{
	@Test
	public void EnviarArquivo() { // Teste para enviar um arquivo
		given()  																											 //Primeira parte é o nome do campo que está adicionando o arquivo
			.log().all() // Log de todas as requisições                                                                      // Segunda parte é o caminho do arquivo 
			.multiPart("arquivo", new File("src\\main\\resources\\txt\\arquivoTeste.txt")) // Adiciona um arquivo multipart -
		.when()
			.post("http://restapi.wcaquino.me/upload") // Envia uma requisição POST para o endpoint de upload
		.then()	
			.log().all() // Log de todas as respostas
			.statusCode(200) // Verifica se o código de status é 200 (OK)
			.body("name", Matchers.is("arquivoTeste.txt")) // Verifica se o nome do arquivo é "arquivoTeste.txt"
			.body("size", Matchers.is(520)) // Verifica se o tamanho do arquivo é 102400 bytes
			.body("md5", is("73a3346cc4f4cf3908600e8b596bf720"))
			;
	}
	
	
}
