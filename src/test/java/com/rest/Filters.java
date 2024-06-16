package com.rest;

import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.ResponseSpecification;

public class Filters {
	
    io.restassured.specification.RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
        
    	PrintStream fileOutPutStream = new PrintStream(new File("restAssured.log"));

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                addFilter(new RequestLoggingFilter(fileOutPutStream)).
                addFilter(new ResponseLoggingFilter(fileOutPutStream));
        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecification = responseSpecBuilder.build();
    }
	
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
	
	
	
	@Test
	public void logginFiltersToFileUsingGlobalConfig() throws FileNotFoundException {
		given(requestSpecification)
		.baseUri("https://postman-echo.com")
		.when()
		.get("/get")
		.then()
		.spec(responseSpecification)
		.assertThat()
		.statusCode(200);
	}
}
