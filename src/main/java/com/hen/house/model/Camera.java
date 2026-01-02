package com.hen.house.model;

public class Camera extends Identifier {
	private String name = "";
	private String url = "";

	public Camera() {
		super();
	}
	
	public Camera(int id, String name, String url) {
		super(id);
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
