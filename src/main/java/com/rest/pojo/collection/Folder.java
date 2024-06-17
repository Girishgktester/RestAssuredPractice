package com.rest.pojo.collection;

import java.util.List;

public class Folder {

	private String name;
	List<RequestRoot> requestRoot;

	public Folder() {

	}

	public Folder(String name, List<RequestRoot> requestRoot) {

		this.name = name;
		this.requestRoot = requestRoot;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RequestRoot> getRequestRoot() {
		return requestRoot;
	}

	public void setRequestRoot(List<RequestRoot> requestRoot) {
		this.requestRoot = requestRoot;
	}

}
