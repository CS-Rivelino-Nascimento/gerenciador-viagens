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

public class BuscaViagem {
	
	static Apoio apoiador = new Apoio();
    static String conecta = apoiador.RetornaBaseUri();
    

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = conecta;

    }

	@Test
	public void ValidaSchema() {
		apoiador.GravaViagem();
		
		String retornoSchema = given().
				when().
				contentType("application/json").
				get().body().asString();
		
		assertThat(retornoSchema, matchesJsonSchemaInClasspath("jsons/schema_busca_viagem.json"));
	}

	@Test
	public void ValidaRetornoBusca(){
		apoiador.DeletaViagem();
		apoiador.GravaViagem();
		
		Response response = given().
				when().
				contentType("application/json").
				get().
				then().
				assertThat().statusCode(200).
				and().
				extract().response();
		
		Assert.assertEquals("[Medina]", response.path("localDeDestino").toString());
		Assert.assertEquals("[2018-01-10]", response.path("dataPartida").toString());
		Assert.assertEquals("[2018-02-10]", response.path("dataRetorno").toString());
		Assert.assertEquals("[Eliete]", response.path("acompanhante").toString());
	}
	
	@Test
	public void BuscaViagemIDNaoCadastrado(){

		Response response = given().
				when().
				contentType("application/json").
				get("10000").
				then().
				assertThat().statusCode(400).
				and().
				extract().response();
		
		Assert.assertEquals("Viagem não encontrada", response.path("mensagem").toString());
	}
}
