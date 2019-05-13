package com.github.winter4666.springmvcplus.utils;

import javax.servlet.http.HttpServletRequest;

public class PowerRequest {
	
	private HttpServletRequest request;
	
	private PowerRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public static PowerRequest wrap(HttpServletRequest request) {
		return new PowerRequest(request);
	}
	
	public String getBearerToken() {
		String authorization = request.getHeader("Authorization");
		return authorization == null ? null : authorization.replace("Bearer ", "");
	}

}
