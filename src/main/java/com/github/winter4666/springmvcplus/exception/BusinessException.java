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
	
	public BusinessException(String msg) {
		super(msg);
		this.retCode = ReservedRetCode.FAIL;
	}
	
	public BusinessException(RetCode retCode) {
		super(retCode.msg());
		this.retCode = retCode;
	}
	
	public BusinessException(RetCode retCode,String msg) {
		super(msg);
		this.retCode = retCode;
	}

	public RetCode getRetCode() {
		return retCode;
	}

}
