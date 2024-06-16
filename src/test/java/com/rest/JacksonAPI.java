package com.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class JacksonAPI {
	

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://api.postman.com").
                addHeader("X-Api-Key", "PMAK-666b4119e710dd000159be39-11e5f039a5e3a8ef9e34601f045d7fa871").
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
    public void sendPayLoadAsJsonArray() throws JsonProcessingException{ 
 		HashMap<String, Object> mainObj = new HashMap<String, Object>();
 		HashMap<String, String> nestedObj = new HashMap<String, String>() ;
 		nestedObj.put("name", "JackonsWorkSpace");
 		nestedObj.put("type", "team");
 		nestedObj.put("description", "This is where the collaboration happens.");
 		mainObj.put("workspace", nestedObj);
 		
 		ObjectMapper mapper = new ObjectMapper();
 		String jsonPayload = mapper.writeValueAsString(mainObj);
 		
    given()
        .body(jsonPayload)
        .when()
        .post("/workspaces")
        .then()
        .log().all()
        .assertThat()
        .statusCode(200)
        .body("workspace.name", equalTo("JackonsWorkSpace"));
	}
}