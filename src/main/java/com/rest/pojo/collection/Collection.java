package com.rest.pojo.collection;

import java.util.List;

public class Collection {

	Info info;
	List<Object> item;

	public Collection() {

	}

	public Collection(Info info, List<Object> items) {

		this.info = info;
		this.item = items;

	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public List<Object> getItem() {
		return item;
	}

	public void setItem(List<Object> item) {
		this.item = item;
	}

}
