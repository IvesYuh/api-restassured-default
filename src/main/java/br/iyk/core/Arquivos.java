package br.iyk.core;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Arquivos {
    private static final String TOKEN_INSTALL = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJTb2x1cyIsImlhdCI6MTcyNjA4NDg0NCwic3ViIjoiNzE4Njc3Iiwia2V5X3B1YmxpYyI6IjE3MjYwNzQwNDQ6UkVTVEFTU1VSRUQiLCJrZXlfdHlwZSI6IkEiLCJ1c3VhcmlvIjoiUkVTVEFTU1VSRUQiLCJhdWQiOiI1Q0U0NjNFMi02NUYyLTREM0EtQTEzMy02MDdDMDJGMDM3NUY6NjhCNDRGNkItNDY3Qi00NjJFLThERTQtQzQ5MzRGMUVGN0JBOiJ9.AkZjDmpV0r5dStkkPxWilil2eEM0w2no2M4xaHmi7lIB75Z7eU1s30t_XfTku7Q_O3EBIFycsfEVDS4Xjo5KmA";
    private static final String ENDERECO = "http://172.16.80.21:15160/v1";
    
    public static String retornaTokenInstall() {
        Map<String, String> body = new HashMap<>();
        body.put("login", "RESTASSURED");
        body.put("senha", "e10adc3949ba59abbe56e057f20f883e");
        body.put("acessToken", TOKEN_INSTALL);
        
        String token =  
        	given()
        		.header("Authorization", TOKEN_INSTALL)
        		.contentType(ContentType.JSON)
        		.body(body)
            .when()
                .post(ENDERECO + "/install")
            .then()
            	.statusCode(200)
            	.extract().path("accessToken")
            ;
        return token;
    }

    public static void retornaToken() {
        Map<String, String> body = new HashMap<>();
        body.put("login", "RESTASSURED");
        body.put("senha", "e10adc3949ba59abbe56e057f20f883e");
        
        String token = 
        	given()
                .header("Authorization", retornaTokenInstall()) 
                .contentType(ContentType.JSON)
                .body(body)
            .when()
                .post(ENDERECO + "/login")
            .then()
                .statusCode(200)
                .extract().path("token")
                ;
        RestAssured.requestSpecification.header("Authorization", "Bearer " + token);
    }
    
    public static String retornaTokenManual() {
        Map<String, String> body = new HashMap<>();
        body.put("login", "RESTASSURED");
        body.put("senha", "e10adc3949ba59abbe56e057f20f883e");
        
        String token = 
        	given()
                .header("Authorization", retornaTokenInstall()) 
                .contentType(ContentType.JSON)
                .body(body)
            .when()
                .post(ENDERECO + "/login")
            .then()
                .statusCode(200)
                .extract().path("token")
                ;
        return token;
    }
    
    public static String retornaTokenRefresh() {
        Map<String, String> body = new HashMap<>();
        body.put("login", "RESTASSURED");
        body.put("senha", "e10adc3949ba59abbe56e057f20f883e");
        
        String token = 
        	given()
                .header("Authorization", retornaTokenInstall()) 
                .contentType(ContentType.JSON)
                .body(body)
            .when()
                .post(ENDERECO + "/login")
            .then()
                .statusCode(200)
                .extract().path("refreshToken")
                ;
        return token;
    }
}
