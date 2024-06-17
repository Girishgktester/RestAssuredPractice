package com.rest.pojo.collection;

import java.util.List;

public class Request {

	private String url;
	private String method;
	private String descrption;
	List<Header> header;
	Body body;

	public Request() {

	}

	public Request(String url, String method, List<Header> header, Body body,String descrption) {
		this.url = url;
		this.method = method;
		this.header = header;
		this.body = body;

	}

	public List<Header> getHeader() {
		return header;
	}

	public void setHeader(List<Header> header) {
		this.header = header;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
