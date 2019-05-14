package com.github.winter4666.springmvcplus.restapi;

/**
 * 保留返回码，分别对应接口调用成功和通用的失败
 * @author wutian
 */
public enum ReservedRetCode implements RetCode {
	SUCCESS(0,"success"),
	FAIL(1,"fail");
    
	private Integer code;
	
	private String msg;

	@Override
	public Integer code() {
		return code;
	}

	@Override
	public String msg() {
		return msg;
	}
	
	ReservedRetCode(Integer code,String msg) {
		this.code = code;
		this.msg = msg;
	}

}
