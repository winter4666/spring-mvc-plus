package com.github.winter4666.springmvcplus.restapi;

/**
 * 接口调用返回结果包装类
 * @author wutian
 */
public class APIResult<T> {

    private Integer code;

    private String msg;

    private T data;
    
    private APIResult() {
    	
    }
    
    /**
     * 接口调用成功，空数据
     * @return
     */
    public static APIResult<?> success() {
    	APIResult<?> apiResult = new APIResult<>();
    	apiResult.code = ReservedRetCode.SUCCESS.code();
    	apiResult.msg = ReservedRetCode.SUCCESS.msg();
    	return apiResult;
    }
    
    /**
     * 接口调用成功，同时返回数据
     * @param data
     * @return
     */
    public static <T> APIResult<T> success(T data) {
    	APIResult<T> apiResult = new APIResult<>();
    	apiResult.code = ReservedRetCode.SUCCESS.code();
    	apiResult.msg = ReservedRetCode.SUCCESS.msg();
    	apiResult.data = data;
    	return apiResult;
    }
    
    /**
     * 接口调用失败
     * @return
     */
    public static APIResult<?> fail() {
    	APIResult<?> apiResult = new APIResult<>();
    	apiResult.code = ReservedRetCode.FAIL.code();
    	apiResult.msg = ReservedRetCode.FAIL.msg();
    	return apiResult;
    }
    
    /**
     * 接口调用失败，自定义返回信息
     * @param msg
     * @return
     */
    public static APIResult<?> fail(String msg) {
    	APIResult<?> apiResult = new APIResult<>();
    	apiResult.code = ReservedRetCode.FAIL.code();
    	apiResult.msg = msg;
    	return apiResult;
    }
    
    /**
     * 接口调用失败，自定义返回码
     * @param retCode
     * @return
     */
    public static APIResult<?> fail(RetCode retCode) {
    	APIResult<?> apiResult = new APIResult<>();
    	apiResult.code = retCode.code();
    	apiResult.msg = retCode.msg();
    	return apiResult;
    }
    
    /**
     * 设置msg字段
     * @param msg
     * @return
     */
    public APIResult<?> msg(String msg) {
    	this.msg = msg;
    	return this;
    }
    
    /**
     * 设置data字段
     * @param data
     * @return
     */
    public APIResult<T> data(T data) {
    	this.data = data;
    	return this;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

}
