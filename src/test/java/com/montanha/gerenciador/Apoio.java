package com.montanha.gerenciador;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class Apoio {
	public String RetornaBaseUri() {
			return "http://localhost:8089/api/viagens/";
	    }
	
	public void GravaViagem(){
		String Json = "{\"localDeDestino\":\"Medina\",\"dataPartida\":\"2018-01-10\",\"dataRetorno\":\"2018-02-10\",\"acompanhante\":\"Eliete\"}";
			given().
				when().
				contentType("application/json").body(Json).
				post("new").
				then().
				assertThat().statusCode(201);
	}
}
