package com.montanha.gerenciador;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import org.junit.Assert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class DeletarViagem {
	static Apoio apoiador = new Apoio();
    static String conecta = apoiador.RetornaBaseUri();
    

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = conecta;

    }

	@Test
	public void Delete() {
		given().when().
		contentType("application/json").delete("8").then().and().assertThat().statusCode(200);
	}
	
	@Test
	public void DeleteIDNaoEncontrado() {
		Response response = given().when().
		contentType("application/json").delete("8").then().and().assertThat().statusCode(400).and().extract().response();
		
		Assert.assertEquals("Viagem não encontrada", response.path("mensagem").toString());
	}

}
