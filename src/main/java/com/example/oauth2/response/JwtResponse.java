package com.example.oauth2.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String username;
	private String email;
	private Long id;
	private List<String> roles;
	public JwtResponse(String token, String username, String email, Long id, List<String> roles) {
		this.token = token;

		this.username = username;
		this.email = email;
		this.id = id;
		this.roles = roles;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
	
	
}
