package com.rest;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;

public class FormAuth {

	@BeforeClass
	public void beforeClass() {
		  RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
	                .setRelaxedHTTPSValidation()
	                .setBaseUri("https://localhost:8443")
	                .log(LogDetail.ALL);
	 
	        RestAssured.requestSpecification = requestSpecBuilder.build();
	 
	        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
	                expectStatusCode(200);
	        RestAssured.responseSpecification = responseSpecBuilder.build();

	}

	@Test
	public void formAuthUsingCsrfToken() {
		
		given().auth().form("dan", "dan123", new FormAuthConfig("/signin", "txtUsername", "txtPassword"))
		.log().all()
		.when()
		.get("/login")
		.then()
		.log()
		.all();
	}
}