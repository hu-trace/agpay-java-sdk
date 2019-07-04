package com.waya.agpaysdk.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.waya.agpaysdk.config.DefaultTokenConfig;
import com.waya.agpaysdk.exception.HttpRequestException;
import com.waya.agpaysdk.exception.HttpResponseException;
import com.waya.agpaysdk.util.QueryStringObject;

public class HttpRequest {
	
	private static final String CONTENT_TYPE = "application/json";
	
	private HttpURLConnection conn;
	private OutputStream out;
	private BufferedReader br;
	
	private void buildConnect(String module, String method, Map<String, String> headers) throws HttpRequestException {
		method = method.toUpperCase();
		try {
			URL url = new URL(DefaultTokenConfig.instance.domain() + module);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.addRequestProperty("Content-Type", CONTENT_TYPE);
			if(!method.equals("GET")) {
				conn.setDoOutput(true);
				conn.setRequestMethod(method);
			}
		}catch (Exception e) {
			throw new HttpRequestException("Domain or model error", e);
		}
		if(headers != null) {
			Set<Entry<String, String>> set = headers.entrySet();
			for(Entry<String, String> entry : set) {
				conn.addRequestProperty(entry.getKey(), entry.getValue());
			}
		}
		connect();
	}
	
	private void connect() throws HttpRequestException {
		try {
			conn.connect();
		}catch (IOException e) {
			throw new HttpRequestException("connection error", e);
		}
	}
	
	private void write(String data) throws HttpRequestException {
		try {
			out = conn.getOutputStream();
			out.write(data.getBytes());
			out.flush();
		}catch (IOException e) {
			throw new HttpRequestException("Data transfer error", e);
		}
	}
	
	private JSONObject read() throws HttpResponseException {
		try {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String str;
			StringBuilder strb = new StringBuilder();
			while((str = br.readLine()) != null) {
				if(str.length() == 0) {
					continue;
				}
				strb.append(str);
			}
			return JSONObject.parseObject(strb.toString());
		}catch (IOException e) {
			throw new HttpResponseException("Data transfer error", e);
		}
	}
	
	public JSONObject get(String module) throws HttpRequestException, HttpResponseException {
		return get(module, null);
	}
	
	public JSONObject post(String module, Map<String, Object> param) throws HttpRequestException, HttpResponseException {
		return post(module, param, null);
	}
	
	public JSONObject get(String module, Map<String, String> headers) throws HttpRequestException, HttpResponseException {
		buildConnect(module, "get", headers);
		return read();
	}
	
	public JSONObject get(String module, HashMap<String, Object> param, Map<String, String> headers) throws HttpRequestException, HttpResponseException {
		String par = QueryStringObject.hashMapKeySortToQueryString(param);
		if(module.indexOf('?') > -1) {
			return get(module + "&" + par, headers);
		}else {
			return get(module + "?" + par, headers);
		}
	}
	
	public JSONObject post(String module, Map<String, Object> param, Map<String, String> headers) throws HttpRequestException, HttpResponseException {
		buildConnect(module, "post", headers);
		write(new JSONObject(param).toString());
		return read();
	}
	
}
