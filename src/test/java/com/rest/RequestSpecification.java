package com.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.matchesPattern;


public class RequestSpecification {

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
	public void validateResponseCodeWithRequestSpecification() {
		given(requestSpecification)
		.log().all()
		.when()
		.get("/workspaces")
		.then().log().all()
		.assertThat()
		.statusCode(200);
	}
	
	@Test
	public void validateResponseCodeUsingRequestSpecification() {
		Response response =	given(requestSpecification)
		.log().all()
		.when()
		.get("/workspaces")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.extract()
		.response();
		JsonPath jsonPath = new JsonPath(response.asString());
		String workspaceName = jsonPath.getString("workspaces[0].name");	
		assertThat(workspaceName, equalTo("My Workspace"));
	}
	
	
	@Test
	public void validatePostRequestBDDStyle() {
		
		given()
		.baseUri("https://api.postman.com").header("x-api-key","PMAK-666b4119e710dd000159be39-11e5f039a5e3a8ef9e34601f045d7fa871")
		.body("{\r\n"
				+ "    \"workspace\": {\r\n"
				+ "        \"name\": \"My WorkspaceRest\",\r\n"
				+ "        \"type\": \"team\",\r\n"
				+ "        \"description\": \"This is created using RestAssured.\"\r\n"
				+ "    }\r\n"
				+ "}")
		
		.when().post("/workspaces")
		.then().log().all()
		.assertThat().statusCode(200)
		.body("workspace.name", equalTo("My WorkspaceRest"));		
	}
	
	 @Test
	    public void validate_put_request_bdd_style(){
	        String workspaceId = "1901da48-f2a9-4d8e-97bc-2b9156b787be";
	        String payload = "{\r\n"
	        		+ "    \"workspace\": {\r\n"
	        		+ "        \"name\": \"My WorkspaceRestssss\",\r\n"
	        		+ "        \"type\": \"team\",\r\n"
	        		+ "        \"description\": \"This is where the collaboration happens. Use this space to share and collaborate on APIs, collections, environments, monitors, and mocks.\"\r\n"
	        		+ "    }\r\n"
	        		+ "}";
	        given().
	                body(payload).
	        when().
	                put("/workspaces/"+workspaceId).
	        then().
	                log().all().
	                assertThat().
	                body("workspace.name", equalTo("My WorkspaceRestssss"),
	                "workspace.id", matchesPattern("^[a-z0-9-]{36}$"),
                    "workspace.id", equalTo(workspaceId));;

		}
	 
	 	@Test
	    public void validate_Delete_request_bdd_style(){
	        String workspaceId = "23748d6d-6d45-4351-9711-5e99296290db";
	        given()
	        .when()
	        .delete("/workspaces/"+workspaceId)
	        .then()
	        .log().all()
	        .assertThat()
	        .statusCode(200).
	        body("workspace.id", matchesPattern("^[a-z0-9-]{36}$"));
		}
	}