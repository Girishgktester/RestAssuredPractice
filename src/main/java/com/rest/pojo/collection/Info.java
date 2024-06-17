package com.rest.pojo.collection;

public class Info {
	
	public Info() {
		
	}

	public Info(String name, String descrption, String schema) {
		this.name = name;
		this.descrption = descrption;
		this.schema = schema;
	}

	private String name;
	private String descrption;
	private String schema;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String screma) {
		this.schema = screma;
	}

}
