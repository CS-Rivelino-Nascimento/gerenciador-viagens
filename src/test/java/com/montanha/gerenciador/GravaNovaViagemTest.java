package com.montanha.gerenciador;

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

import static org.hamcrest.MatcherAssert.assertThat;


public class GravaNovaViagemTest {
	static Apoio apoiador = new Apoio();
    static String conecta = apoiador.RetornaBaseUri();
    

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = conecta;

    }

	@Test
	public void ValidaSchema() {
		String Json = "{\"localDeDestino\":\"Medina\",\"dataPartida\":\"2018-01-10\",\"dataRetorno\":\"2018-02-10\",\"acompanhante\":\"Eliete\"}";
		
		String retornoSchema = given().
				when().
				contentType("application/json").
				body(Json).
				post("new").body().asString();
		
		assertThat(retornoSchema, matchesJsonSchemaInClasspath("jsons/schema_grava_viagem.json"));
	}
	
	@Test
	public void GravaViagemSucesso(){
		String Json = "{\"localDeDestino\":\"Medina\",\"dataPartida\":\"2018-01-10\",\"dataRetorno\":\"2018-02-10\",\"acompanhante\":\"Eliete\"}";
		
		Response response = given().
				when().
				contentType("application/json").body(Json).
				post("new").
				then().
				assertThat().statusCode(201).
				and().
				extract().response();
		
		Assert.assertEquals("Medina", response.path("data.localDeDestino").toString());
		Assert.assertEquals("2018-01-10", response.path("data.dataPartida").toString());
		Assert.assertEquals("2018-02-10", response.path("data.dataRetorno").toString());
		Assert.assertEquals("Eliete", response.path("data.acompanhante").toString());
	}

}
