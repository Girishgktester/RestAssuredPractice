package com.rest;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import org.testng.annotations.Test;

public class RequestParameters {
	String postManKey = "";

	@Test
	public void singleQueryParameter() {
		
        given()
		.baseUri("https://postman-echo.com")
//		.param("foo", "bar1")
		.queryParam("foo", "bar2")
		.log().all()
		.when().get("/get")
		.then()
		.log().all()
		.assertThat()
		.statusCode(200);	
	}
	
	@Test
	public void multiQueryParameter() {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("foo", "bar");
		hashMap.put("foo1","bar1");
        given()
		.baseUri("https://postman-echo.com")
		.queryParams(hashMap)
		.log().all()
		.when().get("/get")
		.then()
		.log().all()
		.assertThat()
		.statusCode(200);	
	}
	
	@Test
	public void multiValueParameter() {
		
        given()
		.baseUri("https://postman-echo.com")
		.queryParam("foo", "bar2","bar1","bar3")	
		.log().all()
		.when().get("/get")
		.then()
		.log().all()
		.assertThat()
		.statusCode(200);	
	}
	
	
	@Test
	public void pathParameter() {
		
        given()
		.baseUri("https://reqres.in")
		.queryParam("userID","2")	
		.log().all()
		.when().get("/api/users")
		.then()
		.log().all()
		.assertThat()
		.statusCode(200);	
	}
	
	@Test
	public void multiPartFormData() {
		
        given()
		.baseUri("https://postman-echo.com")
		.multiPart("foo", "bar1")
		.log().all()
		.when().post("/post")
		.then()
		.log().all()
		.assertThat()
		.statusCode(200);	
	}
	
	@Test
	public void uploadFileMultipartFromData() {
		String fileCintent = "{\"name\":\"temp.txt\",\"parent\":{\"id\":\"123456\"}}";
        given()
		.baseUri("https://postman-echo.com")
		.multiPart("file",new File("temp.txt"))
		.multiPart("attributes",fileCintent,"application/json")
		.log().all()
		.when().post("/post")
		.then()
		.log().all()
		.assertThat()
		.statusCode(200);	
	}
	
	
	@Test
	public void downloadFileMultipartFromData() throws IOException {
     byte[] bytes =   given()
		.baseUri("https://github.com")
		.when().get("/appium/appium/blob/master/packages/appium/sample-code/apps/ApiDemos-debug.apk")
		.then()
		.log().all()
		
		.extract()
		.response()
		.asByteArray();
     
     	OutputStream os = new FileOutputStream(new File("ApiDemos-debug.apk")); 
     	os.write(bytes);
     	os.close();
		
		}
}