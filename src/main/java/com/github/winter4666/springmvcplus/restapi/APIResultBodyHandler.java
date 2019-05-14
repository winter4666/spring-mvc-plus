package com.github.winter4666.springmvcplus.restapi;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.github.winter4666.springmvcplus.utils.PowerResponse;

/**
 * 自定义{@link HandlerMethodReturnValueHandler}，增加对注解{@link APIResultBody}的支持
 * @author wutian
 * @see APIResultBody
 */
public class APIResultBodyHandler implements HandlerMethodReturnValueHandler {

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		return returnType.getMethodAnnotation(APIResultBody.class) != null;
	}

	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest) throws Exception {
		mavContainer.setRequestHandled(true);
		PowerResponse response = PowerResponse.wrap(webRequest.getNativeResponse(HttpServletResponse.class));
		if(returnType.getParameterType() == APIResult.class) {
			response.renderJSON(returnValue);
		} else {
			response.renderJSON(APIResult.success(returnValue));
		}
	}

}
