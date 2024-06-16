package com.rest.pojo.simple;

public class Workspace {
	
	
	public Workspace() {
		
	}
	
	public Workspace(String name,String type,String descrption) {
		this.name = name;
		this.type = type;
		this.descrption = descrption;
	}
	
	private String name;
	private String type;
	private String descrption;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}


}
