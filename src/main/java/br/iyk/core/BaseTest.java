package br.iyk.core;

import org.junit.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

public class BaseTest {
	@BeforeClass
	public static void endereco() {
        RestAssured.baseURI = "http://172.16.80.21";
        RestAssured.port = 15190;
        RestAssured.basePath = "/v1";
        
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder.setContentType(ContentType.JSON);
		RestAssured.requestSpecification = requestSpecBuilder.build();

    }
}
