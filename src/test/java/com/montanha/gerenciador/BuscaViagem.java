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

}
