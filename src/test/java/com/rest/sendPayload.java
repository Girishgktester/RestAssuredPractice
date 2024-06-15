package com.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class sendPayload {
	
	 @BeforeClass
	    public void beforeClass(){
	        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
	                setBaseUri("https://api.postman.com").
	                addHeader("x-api-key", "PMAK-666b4119e710dd000159be39-11e5f039a5e3a8ef9e34601f045d7fa871").
	                setContentType(ContentType.JSON).
	                log(LogDetail.ALL);
	        RestAssured.requestSpecification = requestSpecBuilder.build();

	        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
	                expectStatusCode(200).
	                expectContentType(ContentType.JSON).
	                log(LogDetail.ALL);
	        RestAssured.responseSpecification = responseSpecBuilder.build();
	    }
	 
	 	@Test
	    public void sendPayLoadAsFile(){ 
	 		File file = new File("src//main//resources//CreateWorkspacePayload.json");
	        given()
	        .body(file)
	        .when()
	        .post("/workspaces")
	        .then()
	        .log().all()
            .assertThat()
            .statusCode(200)
            . body("workspace.name", equalTo("sendWorkspaceAsPayloadFile"));
		}
	 	
	 	
	 	@Test
	    public void sendPayLoadAsObject(){ 
	 		HashMap<String, Object> mainObj = new HashMap<String, Object>();
	 		HashMap<String, String> nestedObj = new HashMap<String, String>() ;
	 		nestedObj.put("name", "sendWorkspaceAsObejct");
	 		nestedObj.put("type", "team");
	 		nestedObj.put("description", "This is where the collaboration happens.");
	 		mainObj.put("workspace", nestedObj);
	 		
	    given()
	        .body(mainObj)
	        .when()
	        .post("/workspaces")
	        .then()
	        .log().all()
            .assertThat()
            .statusCode(200)
            .body("workspace.name", equalTo("sendWorkspaceAsObejct"));
		}
	 	
		
	 	@Test
	    public void sendPayLoadAsJsonArray(){ 
	 		HashMap<String, Object> mainObj = new HashMap<String, Object>();
	 		HashMap<String, String> nestedObj = new HashMap<String, String>() ;
	 		nestedObj.put("name", "sendWorkspaceAsObejct");
	 		nestedObj.put("type", "team");
	 		nestedObj.put("description", "This is where the collaboration happens.");
	 		mainObj.put("workspace", nestedObj);
	 		
	    given()
	        .body(mainObj)
	        .when()
	        .post("/workspaces")
	        .then()
	        .log().all()
            .assertThat()
            .statusCode(200)
            .body("workspace.name", equalTo("sendWorkspaceAsObejct"));
		}
	 	
	 	
	}