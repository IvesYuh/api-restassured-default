package br.iyk.validacaoSchema;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import br.iyk.core.BaseToken;
import br.iyk.entidades.DadosSchemaJson;
import io.restassured.response.Response;

public class SchemaJson {
	    @Test
	    public void validaSchemaJson() throws Exception {
	    	DadosSchemaJson dados = new DadosSchemaJson("32282", "1F0D24F7-87F6-4895-85EB-5D33CFD08D0E");
	    	
	        Response response = given() // Realizando a requisição e obtendo a resposta
	        		.auth().oauth2(BaseToken.retornaTokenManual())
	        		.pathParam("idGuia", dados.getIdGuia())
	        		.pathParam("keyGuia", dados.getKeyGuia())
	        	.when()
	        		.get("http://172.16.80.21:15049/v1/guia/{idGuia}/anexo?appKey={keyGuia}")
	            .then()
	            	.statusCode(200) // Verifica se o status é 200
	            	.extract().response()
	            	;

	        ObjectMapper objectMapper = new ObjectMapper(); // Carregando o schema JSON
	        JsonNode schemaNode = objectMapper.readTree(new File("src\\main\\resources\\schema\\schema.json"));
	        
	        JsonNode responseNode = objectMapper.readTree(response.asString()); // Converte o conteúdo da resposta da API para o formato JsonNode

	        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();     // Validando a resposta com o schema
	        JsonSchema schema = factory.getJsonSchema(schemaNode);
	        ProcessingReport report = schema.validate(responseNode); // Validação do JSON de resposta (responseNode) contra o esquema (schema).

	        if (!report.isSuccess()) { 	      							  // Verificando se a validação foi bem-sucedida
	            for (ProcessingMessage message : report) {
	                System.out.println(message);
	            }
	            throw new AssertionError("Schema validado com falha !");
	        } else {
	            System.out.println("Schema validado com sucesso !");
	        }
	    }
	}