package com.github.winter4666.springmvcplus.restapi;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.github.winter4666.springmvcplus.exception.BusinessException;

/**
 * spring mvc全局异常处理，所有错误以json字符串的形式输出
 * @author wutian
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static Logger logger  = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	@Autowired
    private HttpServletRequest httpServletRequest;

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
		HttpStatus status, WebRequest request) {
    	//获取请求的url参数
		StringBuilder mapStr = new StringBuilder();
		Map<?,?> map = request.getParameterMap();
		boolean addComma = false;
		for(Map.Entry<?,?> entry : map.entrySet()) {
			if(addComma) {
				mapStr.append(",");
			} else {
				addComma = true;
			}
			String[] value = (String[])entry.getValue();
			mapStr.append(entry.getKey() + ":" + (value.length <= 0 ? "null" : value.length <= 1 ? value[0] : Arrays.toString(value)));
		}
    	logger.error("visit " + httpServletRequest.getRequestURL() + " error,param=" + mapStr.toString(), ex);
    	APIResult<?> apiResult = APIResult.fail(ex.getMessage());
	    return new ResponseEntity<Object>(apiResult, status);
	}
	
	@ExceptionHandler(value={
			BusinessException.class
		})
	public ResponseEntity<Object> handleBusinessException(Exception ex, WebRequest request) {
		APIResult<?> apiResult = APIResult.fail(((BusinessException)ex).getRetCode()).msg(ex.getMessage());;
	    return new ResponseEntity<Object>(apiResult, HttpStatus.OK);
	}
	
	@ExceptionHandler(value={
			Exception.class
		})
	public ResponseEntity<Object> handleUnknownException(Exception ex, WebRequest request) {
	    return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	
	
	
}
