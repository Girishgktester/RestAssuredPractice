package com.rest;

import org.testng.annotations.Test;

import io.restassured.config.LogConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestRest {
	
	String postManKey = "PMAK-666b4119e710dd000159be39-11e5f039a5e3a8ef9e34601f045d7fa871";
	
	@Test
	public void testRest() {
		
		given()
		.baseUri("https://api.postman.com").header("x-api-key",postManKey)
		.when().get("/workspaces")
		.then().log().all()
		.assertThat().statusCode(200)
		.body("workspaces.name", hasItem("myWorkSpace"));
		
	}
	
	
	@Test
	public void extractResponse() {
		
	Response res= given()
		.baseUri("https://api.postman.com").header("x-api-key",postManKey)
		.when().get("/workspaces")
		.then()
		.assertThat().statusCode(200)
		.extract()
		.response();
		System.out.println(res.asString());
		
	}
	
	@Test
	public void extractSingleValueFromResponse() {
		
	Response res= given()
		.baseUri("https://api.postman.com").header("x-api-key",postManKey)
		.when().get("/workspaces")
		.then()
		.assertThat().statusCode(200)
		.extract()
		.response();
		
		// 3 ways to fetch json response
		// 1st way using response.path
		System.out.println("workspace names " +res.path("workspaces[0].name"));
	
		// 2nd way using response from method we need to pass response as string
		System.out.println(JsonPath.from(res.asString()).getString("workspaces[0].name"));

		// 3rd way using Jsonpath class
		JsonPath jsonPath = new JsonPath(res.asString());
		System.out.println(jsonPath.getString("workspaces[0].name"));	
	}
	
	@Test
	public void hamcrestAssertOnExtracedResponse() {
	Response name= given()
		.baseUri("https://api.postman.com").header("x-api-key",postManKey)
		.when().get("/workspaces")
		.then()
		.assertThat().statusCode(200)
		.extract()
		.response();
		JsonPath jsonPath = new JsonPath(name.asString());
		String workspaceName = jsonPath.getString("workspaces[0].name");	
		assertThat(workspaceName, equalTo("My Workspace"));
	}
	
	
	@Test
	public void validateHamcrest() {
		
		given()
		.baseUri("https://api.postman.com").header("x-api-key",postManKey)
		.when().get("/workspaces")
		.then()
		.assertThat().statusCode(200)
		.body("workspaces.name", contains("My Workspace","Test","My Workspace","myWorkSpace"),
		"workspaces.name",is(not(empty())));
		
	}
	
	
	@Test
	public void printHeader() {
		given()
		.baseUri("https://api.postman.com").header("x-api-key",postManKey)
		.log().headers()
		.when().get("/workspaces")
		.then()
		.log().body()
		.assertThat().statusCode(200)
		.body("workspaces.name", contains("My Workspace","Test","My Workspace","myWorkSpace"),
		"workspaces.name",is(not(empty())));
	}
	
	@Test
	public void logIfError() {
		given()
		.baseUri("https://api.postman.com").header("x-api-key",postManKey)
		.log().all()
		.when().get("/workspaces")
		.then()
		.log().ifError()
		.assertThat().statusCode(200);
	}
	

	@Test
	public void logIfErrorOnlyIfValidationFails() {
		given()
		.baseUri("https://api.postman.com").header("x-api-key",postManKey)
		.config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
		//line number 112 will print response and request if validation fails
//		.log().ifValidationFails() this will print the request 
		.when().get("/workspaces")
		.then()
//		.log().ifValidationFails() this will print the response only
		.assertThat().statusCode(200);
	}
	
	@Test
	public void bliackListHeaders() {
		Set<String> set = new HashSet<String>();
		set.add("x-api-key");
		set.add("Accept");
		given()
		.baseUri("https://api.postman.com").header("x-api-key",postManKey)
		.config(config.logConfig(LogConfig.logConfig().blacklistHeader("x-api-key")))
//		.config(config.logConfig(LogConfig.logConfig().blacklistHeaders(set))) blcklist multiple headers
		.log().all()
		//blacklist headers -api-key=[ BLACKLISTED ]
		.when().get("/workspaces")
		.then()
		.assertThat().statusCode(200);
	}
	
	@Test
	public void headers() {
		
		given()
		.baseUri("https://55a45fde-4976-478f-b7d3-c86e453ce14d.mock.pstmn.io").header("x-api-key",postManKey)
		.headers("header","value1")
		.headers("x-mock-match-request-body","header")
		.log().all()
		.when().get("/get")
		.then()
		.log().all()
		.assertThat().statusCode(200);
	}
	
	
	@Test
	public void multi_headers() {
		Header header = new Header("header", "value1");
		Header matchHeader = new Header("x-mock-match-request-body", "header");
		Headers multiheader = new Headers(header, matchHeader);
		given()
		.baseUri("https://55a45fde-4976-478f-b7d3-c86e453ce14d.mock.pstmn.io").header("x-api-key",postManKey)
		.headers(multiheader)
		.log().all()
		.when().get("/get")
		.then()
		.log().all()
		.assertThat().statusCode(200);
	}
	

	@Test
	public void map_headers() {
		
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("header", "value1");
		headers.put("x-mock-match-request-body", "header");
		given()
		.baseUri("https://55a45fde-4976-478f-b7d3-c86e453ce14d.mock.pstmn.io").header("x-api-key",postManKey)
		.headers(headers)
		.log().all()
		.when().get("/get")
		.then()
		.log().all()
		.assertThat().statusCode(200);
	}
	
	
	@Test
	public void multivalue_headers() {
		
		Header header1 = new Header("multivalueHeader", "value1");
		Header header2 = new Header("multivalueHeader", "value2");
		Headers multiheader = new Headers(header1, header2);
		given()
		.baseUri("https://55a45fde-4976-478f-b7d3-c86e453ce14d.mock.pstmn.io").header("x-api-key",postManKey)
		.headers(multiheader)
		.log().all()
		.when().get("/get")
		.then()
		.log().all()
		.assertThat().statusCode(200);
	}
	
	
	@Test
	public void assertHeaders() {
		
		Header header1 = new Header("multivalueHeader", "value1");
		Header header2 = new Header("multivalueHeader", "value2");
		Headers multiheader = new Headers(header1, header2);
		
		Headers extractrHeaders = given()
		.baseUri("https://55a45fde-4976-478f-b7d3-c86e453ce14d.mock.pstmn.io").header("x-api-key",postManKey)
		.headers(multiheader)
		.log().all()
		.when().get("/get")
		.then()
		.log().all()
		.assertThat().statusCode(200)
		.extract()
		.headers();
		
		for(Header headers : extractrHeaders) {
			System.out.println("--------> " + headers.getName());
			System.out.println("--------> " + headers.getValue());

		}
		
		List<String> lst =   extractrHeaders.getValues("multiValueHeder");
//		
		System.out.println(" extract "+extractrHeaders.get("responseHeader").getName());
		System.out.println("extract extract "+extractrHeaders.get("responseHeader").getValue());

	}
	
}