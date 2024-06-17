package com.rest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.pojo.collection.*;

import groovy.json.JsonException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

public class ComplexPojoTest {
    ResponseSpecification responseSpecification;

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
        responseSpecification = responseSpecBuilder.build();
    }
    

    @Test
    public void complex_pojo_create_collection() throws JsonProcessingException, JsonException {
        Header header = new Header("Content-Type", "application/json");
        List<Header> headerList = new ArrayList<Header>();
        headerList.add(header);

        Body body = new Body("raw", "{\"data\": \"123\"}");
        
        Request request = new Request("https://postman-echo.com/post","POST",headerList,body,"this");
        
        

      

//        String collectionUid = given().
//                body(collectionRoot).
//        when().
//                post("/collections").
//        then().spec(responseSpecification).
//            extract().
//                response().path("collection.uid");
//
//         given().
//                pathParam("collectionUid", collectionUid).
//        when().
//                get("/collections/{collectionUid}").
//        then().spec(responseSpecification).
//                extract().
//                response();
    }
}