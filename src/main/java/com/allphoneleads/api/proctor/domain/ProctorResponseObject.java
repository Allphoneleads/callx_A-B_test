package com.allphoneleads.api.proctor.domain;

public class ProctorResponseObject {
	
	private long id ;
	private String content;
	
	
	public ProctorResponseObject() {
	}

	public ProctorResponseObject(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

}
