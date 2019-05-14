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
	
	/**
	 * 得到BearerToken
	 * @return
	 */
	public String getBearerToken() {
		String authorization = request.getHeader("Authorization");
		return authorization == null ? null : authorization.replace("Bearer ", "");
	}
	
	/**
	 * 得到客户端ip
	 * @return
	 */
    public String getClientIp() {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

}
