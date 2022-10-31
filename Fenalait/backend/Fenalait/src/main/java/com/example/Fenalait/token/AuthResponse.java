package com.example.Fenalait.token;

public class AuthResponse {

	private String email;
	private String tokenRefresh;
	private String tokenAccess;
	
	public AuthResponse() {}
	
	public AuthResponse(String email, String tokenAccess,  String tokenRefresh) {
		this.email = email;
		this.tokenAccess = tokenAccess;
		this.tokenRefresh = tokenRefresh;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getTokenRefresh() {
		return tokenRefresh;
	}

	public void setTokenRefresh(String tokenRefresh) {
		this.tokenRefresh = tokenRefresh;
	}

	public String getTokenAccess() {
		return tokenAccess;
	}

	public void setTokenAccess(String tokenAccess) {
		this.tokenAccess = tokenAccess;
	}
	
	
	
}
