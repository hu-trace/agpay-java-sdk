package com.waya.agpaysdk.util;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

public class Utils {
	
	public static String md5Upper(String data, String charset) throws Exception {
		java.security.MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] array = md.digest(data.getBytes(charset));
		StringBuilder sb = new StringBuilder();
		for(byte item : array) {
			sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString().toUpperCase();
	}
	
	public static String md5Upper(String data) throws Exception {
		return md5Upper(data, "utf-8");
	}
	
	public static String md5Lower(String data, String charset) throws Exception {
		java.security.MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] array = md.digest(data.getBytes(charset));
		StringBuilder sb = new StringBuilder();
		for(byte item : array) {
			sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString().toLowerCase();
	}

	public static String md5Lower(String data) throws Exception {
		return md5Lower(data, "utf-8");
	}
	
	/**
	 * <p>签名回调接口的参数，可用于验证回调接口的参数是否匹配
	 * @param json 回调接口接收的参数
	 * @param randomStr 随机字符串，创建订单时生成的
	 * @return 签名，可以与参数中的sign做比较
	 * @throws Exception 字符编码错误时出现异常
	 */
	public static String notifySign(JSONObject json, String randomStr) throws Exception {
		return notifySign(json.getInnerMap(), randomStr);
	}
	
	/**
	 * <p>签名回调接口的参数，可用于验证回调接口的参数是否匹配
	 * @param map 回调接口接收的参数
	 * @param randomStr 随机字符串，创建订单时生成的
	 * @return 签名，可以与参数中的sign做比较
	 * @throws Exception 字符编码错误时出现异常
	 */
	public static String notifySign(Map<String, Object> map, String randomStr) throws Exception {
		HashMap<String, Object> par = new HashMap<>();
		Set<Entry<String, Object>> set = map.entrySet();
		for(Entry<String, Object> entry : set) {
			if(entry.getKey().equals("sign")) {
				continue;
			}
			par.put(entry.getKey(), entry.getValue());
		}
		String qs = QueryStringObject.hashMapKeySortToQueryString(par);
		return md5Upper(randomStr + "." + qs);
	}

}
