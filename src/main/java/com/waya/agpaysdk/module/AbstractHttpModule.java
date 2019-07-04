package com.waya.agpaysdk.module;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.waya.agpaysdk.config.DefaultRequestConfig;
import com.waya.agpaysdk.exception.HttpRequestException;
import com.waya.agpaysdk.exception.HttpResponseException;
import com.waya.agpaysdk.http.HttpRequest;
import com.waya.agpaysdk.util.QueryStringObject;
import com.waya.agpaysdk.util.Utils;

/**
 * <p>请求WAYA Pay接口的抽象模块, 继承此模块即可快速请求WAYA Pay
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see HttpModule
 * @since 1.8
 * @version 1.0
 * @time 2019年7月3日
 */
public abstract class AbstractHttpModule implements HttpModule {
	
	private final Map<String, String> headers;
	
	AbstractHttpModule() {
		headers = new HashMap<>();
		headers.put("Scpsat-Token", DefaultRequestConfig.instance.token());
		headers.put("Scpsat-Char", DefaultRequestConfig.instance.sessionChar());
	}
	
	private HashMap<String, Object> parameter;
	
	/**
	 * <p>获取请求接口
	 * @return 请求接口
	 */
	abstract String module();
	
	/**
	 * <p>获取请求类型
	 * @return 请求类型
	 */
	abstract String method();
	
	@Override
	public void parameter(JSONObject param) throws HttpRequestException {
		parameter((HashMap<String, Object>) param.getInnerMap());
	}
	
	@Override
	public void parameter(HashMap<String, Object> param) throws HttpRequestException {
		parameter = param;
		if(!method().equalsIgnoreCase("get")) {
			String queryString = QueryStringObject.hashMapKeySortToQueryString(parameter);
			String sign;
			try {
				sign = Utils.md5Lower(DefaultRequestConfig.instance.scpsatSecretKey() + "." + queryString);
			}catch (Exception e) {
				throw new HttpRequestException("Signature setting error", e);
			}
			headers.put("Scpsat-Sign", sign);
		}
	}
	
	/**
	 * <p>执行
	 * @return 请求响应数据
	 * @throws HttpResponseException 
	 * @throws HttpRequestException
	 */
	@Override
	public JSONObject execute() throws HttpRequestException, HttpResponseException {
		HttpRequest request = new HttpRequest();
		String module = module() + "?clientId=" + DefaultRequestConfig.instance.clientId();
		if(method().equalsIgnoreCase("get")) {
			return request.get(module, parameter, headers);
		}else if(method().equalsIgnoreCase("post")) {
			return request.post(module, parameter, headers);
		}else {
			throw new HttpRequestException("There is no such request [ " + method() + " ]");
		}
	}
	
}
