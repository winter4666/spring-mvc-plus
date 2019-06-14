package com.github.winter4666.springmvcplus.exception;

import com.github.winter4666.springmvcplus.restapi.ReservedRetCode;
import com.github.winter4666.springmvcplus.restapi.RetCode;

/**
 * 业务异常，仅把错误信息返回到前端，不打日志
 * @author wutian
 */
public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private RetCode retCode;
	
	private String msg;
	
	private Object data;
	
	public BusinessException(RetCode retCode,String msg,Object data) {
		super(msg);
		this.retCode = retCode;
		this.msg = msg;
		this.data = data;
	}
	
	public BusinessException(RetCode retCode,String msg) {
		this(retCode, msg, null);
	}
	
	public BusinessException(RetCode retCode) {
		this(retCode,retCode.msg());
	}
	
	public BusinessException(String msg) {
		this(ReservedRetCode.FAIL, msg);
	}

	public RetCode getRetCode() {
		return retCode;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public Object getData() {
		return data;
	}
	
}
