package com.rest;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;

public class Filters {
	
	@Test
	public void loggingFilters() {
		given()
		.baseUri("https://postman-echo.com")
		.filter(new RequestLoggingFilter(LogDetail.BODY))
		.when()
		.get("/get")
		.then()
		.assertThat()
		.statusCode(200);
	}

}
