package com.rest;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchemaValidation {
	

	@Test
	public void validateJsonSchema() {
		given()
		.baseUri("https://postman-echo.com")
		.when().get("/get")
		.then().log().all()
		.assertThat().statusCode(200)
		.body(matchesJsonSchemaInClasspath("jsonSchema.json"));
	}

	
}
