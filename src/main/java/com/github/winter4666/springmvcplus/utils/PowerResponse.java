package com.github.winter4666.springmvcplus.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PowerResponse {
	
	private static ObjectMapper objectMapper;
	
	private HttpServletResponse response;
	
	private PowerResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	private static ObjectMapper getObjectMapper() {
		if(objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		return objectMapper;
	}
	
	public static PowerResponse wrap(HttpServletResponse response) {
		return new PowerResponse(response);
	}
	
	/**
	 * 从上下文中获取对象
	 * @return
	 * @see org.springframework.web.context.request.ServletWebRequest#getResponse()
	 */
	public static PowerResponse getInstanceFromContext() {
		HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
		return new PowerResponse(response);
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
	
	/**
	 * 设置文件名
	 * @param fileName
	 * @throws UnsupportedEncodingException 
	 */
	public void setFileName(String fileName) throws UnsupportedEncodingException {
	    HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
	    String userAgent = request.getHeader("User-Agent");  
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
        	//针对IE或者以IE为内核的浏览器：  
            fileName = URLEncoder.encode(fileName, "UTF-8");  
        } else {  
            //非IE浏览器的处理：  
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");  
        }
        response.setHeader("Content-disposition", "attachment;filename=\""+ fileName + "\""); 
	}
	
	
	/**
	 * 向前端返回文件
	 * @param response
	 * @param bytes
	 * @param contentType
	 * @param fileName
	 * @throws IOException
	 */
	public void renderFile(byte[] bytes,String contentType,String fileName) throws IOException {
		response.setContentType(contentType); 
		if(fileName != null && !"".equals(fileName)) {
			setFileName(fileName);
		}
		response.setContentLength(bytes.length);
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			out.write(bytes);
		} finally {
			if(out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * 向前端返回文件
	 * @param response
	 * @param inputStream
	 * @param contentType
	 * @throws IOException
	 */
	public void renderFile(InputStream inputStream,String contentType) throws IOException {
		response.setContentType(contentType); 
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
	        int length = 0;
	        byte[] buffer = new byte[1024];
	        while((length = inputStream.read(buffer)) != -1){
	        	out.write(buffer,0,length);
	        }
		} finally {
			if(out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * 向前端返回文件
	 * @param response
	 * @param bytes
	 * @param contentType
	 * @param fileName
	 * @throws IOException
	 */
	public void renderExcelFile(byte[] bytes,String fileName) throws IOException {
		renderFile(bytes, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", fileName);
	}
	
	/**
	 * 向前端返回字符串
	 * @param response
	 * @param str
	 * @param contentType
	 */
	public void renderString(String str,String contentType) throws IOException {
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType(contentType);
	    PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(str);
		} finally {
			if(writer != null) {
				writer.close();
			}
		}
	}
	
	/**
	 * 向前端返回json串
	 * @param object
	 * @return
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	public void renderJSON(Object object) throws IOException {
		renderString(getObjectMapper().writeValueAsString(object), "application/json");
	}

}
