package com.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.rest.pojo.simple.Workspace;
import com.rest.pojo.simple.WorkspaceRoot;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class PracticePojo {
	
	io.restassured.specification.RequestSpecification requestSpecification;
    
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
