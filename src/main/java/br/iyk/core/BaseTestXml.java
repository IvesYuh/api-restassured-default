package br.iyk.core;

import org.junit.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

public class BaseTestXml {
	@BeforeClass
	public static void endereco() {
        RestAssured.baseURI = "http://172.16.80.3";
        RestAssured.port = 2000;
        RestAssured.basePath = "/TISSSolus40100";
        
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder.setContentType("text/xml");
		requestSpecBuilder.addHeader("SOAPAction","findSoapAction");
		RestAssured.requestSpecification = requestSpecBuilder.build();
    }
}
