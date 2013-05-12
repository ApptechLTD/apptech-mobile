package com.apptech.first.shared.model;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserModel implements IsSerializable{

	private String name;
	private Long id;
	
	private String authToken;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserModel(){
		
	}
	
	public UserModel(long id, String name, String authToken) {
		this.id = id;
		this.name = name;
		this.authToken = authToken;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
}
