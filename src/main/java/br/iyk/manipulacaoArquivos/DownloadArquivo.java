package br.iyk.manipulacaoArquivos;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

public class DownloadArquivo {
	@Test
	public void DownloadDeArquivo() throws IOException {
		byte[] image = given()
			.log().all() // Log de todas as requisições
		.when()
			.get("http://restapi.wcaquino.me/download") // Envia uma requisição GET para o endpoint de download
		.then()
			.statusCode(200) // Verifica se o código de status é 200 (OK)
			.extract().asByteArray() // Extrai a resposta como um array de bytes
			; 
		File imagem = new File("src\\main\\resources\\png\\imagemTeste.jpeg"); // Cria um novo arquivo para armazenar a imagem
		OutputStream out = new FileOutputStream(imagem); // Cria um fluxo de saída para o arquivo
		out.write(image); // Escreve os bytes da imagem no arquivo
		out.close(); // Fecha o fluxo de saída
		System.out.println(image.length); // Imprime o tamanho da imagem
	}
}
