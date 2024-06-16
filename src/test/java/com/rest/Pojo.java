package com.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.pojo.simple.SimplePojo;
import com.rest.pojo.simple.Workspace;
import com.rest.pojo.simple.WorkspaceRoot;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class Pojo {
	
    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://55a45fde-4976-478f-b7d3-c86e453ce14d.mock.pstmn.io").
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
    public void simplePojo() throws JsonProcessingException{ 

		SimplePojo simplePojo = new SimplePojo();
		simplePojo.setKey1("value1");
		simplePojo.setKey2("value2");
		given()
        .body(simplePojo)
        .when()
        .post("/get")
        .then()
        .spec(RestAssured.responseSpecification)
        .log().all()
        .assertThat()
        .statusCode(200)
        .body("key1", equalTo(simplePojo.getKey1()),"key2", equalTo(simplePojo.getKey2()));
	}
	
	@Test
    public void simplePojoDeseralise() throws JsonProcessingException{ 

		SimplePojo simplePojo = new SimplePojo();
		simplePojo.setKey1("value1");
		simplePojo.setKey2("value2");
		SimplePojo deseralisedPojo =	given()
        .body(simplePojo)
        .when()
        .post("/get")
        .then()
        .spec(RestAssured.responseSpecification)
       .extract()
       .response()
       .as(simplePojo.getClass());
		ObjectMapper maper = new ObjectMapper();
		String deseralisedpojoString = maper.writeValueAsString(deseralisedPojo);
		String simplePojoString = maper.writeValueAsString(simplePojo);
		assertThat(maper.readTree(deseralisedpojoString),equalTo(maper.readTree(simplePojoString)));

	}
	
	@Test
    public void simplePojoDeseralise1() throws JsonProcessingException{ 
		Workspace workspace = new Workspace("Myworkspace4","Personal","This is created using pojo");
		WorkspaceRoot workspaceroot = new WorkspaceRoot(workspace);
	
	
		WorkspaceRoot workroot = given()
        .body(workspaceroot)
        .when()
        .post("/workspaces")
        .then()
        .spec(RestAssured.responseSpecification)
       .extract()
       .response()
       .as(WorkspaceRoot.class);
		
		assertThat(workroot.getWorkspace().getName(),equalTo("Myworkspace4"));
		

	}
}