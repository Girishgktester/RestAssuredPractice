package com.rest;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.testng.annotations.Test;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class Filters {
	
	@Test
	public void loggingFilters() {
		given()
		.baseUri("https://postman-echo.com")
		.filter(new RequestLoggingFilter(LogDetail.BODY))
		.filter(new ResponseLoggingFilter(LogDetail.STATUS))
		.when()
		.get("/get")
		.then()
		.assertThat()
		.statusCode(200);
	}
	
	@Test
	public void logginFiltersToFile() throws FileNotFoundException {
		PrintStream fileStream = new PrintStream(new File("restAssured.log"));
		given()
		.baseUri("https://postman-echo.com")
		.filter(new RequestLoggingFilter(LogDetail.BODY,fileStream))
		.filter(new ResponseLoggingFilter(LogDetail.STATUS,fileStream))
		.when()
		.get("/get")
		.then()
		.assertThat()
		.statusCode(200);
	}

}
