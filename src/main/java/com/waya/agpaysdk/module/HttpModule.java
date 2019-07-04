package com.waya.agpaysdk.module;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.waya.agpaysdk.exception.HttpRequestException;
import com.waya.agpaysdk.exception.HttpResponseException;

/**
 * <p>Http请求接口的顶级接口
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see 
 * @since 1.8
 * @version 1.0
 * @time 2019年7月3日
 */
public interface HttpModule {
	
	/**
	 * <p>设置请求参数
	 * @param param 请求参数
	 */
	void parameter(HashMap<String, Object> param) throws HttpRequestException;
	
	/**
	 * <p>设置请求参数
	 * @param param 请求参数
	 */
	void parameter(JSONObject param) throws HttpRequestException;
	
	/**
	 * <p>执行
	 * @return 响应数据{@link JSONObject}
	 * @throws HttpResponseException 
	 * @throws HttpRequestException
	 */
	JSONObject execute() throws HttpRequestException, HttpResponseException;
	
}
