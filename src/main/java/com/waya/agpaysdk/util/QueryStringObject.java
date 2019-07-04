package com.waya.agpaysdk.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

/**
 * <p>QueryString对象类, 包含常用的方法
 * <p>将QueryString格式的字符串转成{@link JSONObject}
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see JSONObject
 * @since 1.8
 * @version 1.0
 * @time 2019年6月29日
 */
public class QueryStringObject {
	
	/**
	 * <p>将queryString格式字符串转成{@link JSONObject}对象
	 * @param text queryString格式字符串
	 * @return {@link JSONObject}
	 */
	public static JSONObject parse(String text) {
		return parse(text, "utf-8");
	}
	
	/**
	 * <p>将queryString格式字符串转成{@link JSONObject}对象
	 * @param text queryString格式字符串
	 * @param charset 编码
	 * @return {@link JSONObject}
	 */
	public static JSONObject parse(String text, String charset) {
		return new JSONObject(parseToMap(text, charset));
	}
	
	/**
	 * <p>将queryString格式字符串转成{@link Map}对象
	 * @param text queryString格式字符串
	 * @return {@link Map}
	 */
	public static Map<String, Object> parseToMap(String text) {
		return parseToMap(text, "utf-8");
	}
	
	/**
	 * <p>将queryString格式字符串转成{@link Map}对象
	 * @param text queryString格式字符串
	 * @param charset 编码
	 * @return {@link Map}
	 */
	public static Map<String, Object> parseToMap(String text, String charset) {
		Map<String, Object> map = new HashMap<>();
		String[] arr = text.split("&");
		String[] group;
		for(int i = 0; i < arr.length; i++) {
			group = arr[i].split("=");
			try {
				map.put(group[0].trim(), URLDecoder.decode(group[1], charset).trim());
			}catch (UnsupportedEncodingException e) {
				map.put(group[0].trim(), group[1].trim());
			}
		}
		return map;
	}
	
	/**
	 * <p>HashMap根据Key排序后并转成queryString字符串
	 * @param map
	 * @param encode 是否将value使用{@link URLEncoder#encode(String, String)}进行转码
	 * @return 排序后的queryString字符串
	 */
	public static String hashMapKeySortToQueryString(HashMap<String, Object> map, boolean encode) {
		return hashMapKeySortToQueryString(map, "utf-8", encode);
	}
	
	/**
	 * <p>HashMap根据Key排序后并转成queryString字符串，并将value用{@link URLEncoder#encode(String, String)}进行转码
	 * @param map
	 * @return 排序后的queryString字符串
	 */
	public static String hashMapKeySortToQueryString(HashMap<String, Object> map) {
		return hashMapKeySortToQueryString(map, "utf-8", true);
	}
	
	/**
	 * <p>HashMap根据Key排序后并转成queryString字符串，并将value用{@link URLEncoder#encode(String, String)}进行转码
	 * @param map
	 * @param charset
	 * @return 排序后的queryString字符串
	 */
	public static String hashMapKeySortToQueryString(HashMap<String, Object> map, String charset) {
		return hashMapKeySortToQueryString(map, charset, true);
	}
	
	/**
	 * <p>HashMap根据Key排序后并转成queryString字符串
	 * @param map
	 * @param charset
	 * @param encode 是否将value使用{@link URLEncoder#encode(String, String)}进行转码
	 * @return 排序后的queryString字符串
	 */
	public static String hashMapKeySortToQueryString(HashMap<String, Object> map, String charset, boolean encode) {
		Set<String> keySet = map.keySet();
		String[] keyArray = keySet.toArray(new String[keySet.size()]);
		Arrays.sort(keyArray);
		StringBuilder sb = new StringBuilder();
		Object value;
		if(encode) {
			for(String k : keyArray) {
				value = map.get(k);
				if(value != null && value.toString().trim().length() > 0) {// 参数值为空，则不参与签名
					try {
						sb.append(k).append("=").append(URLEncoder.encode(value.toString().trim(), charset)).append("&");
					}catch (UnsupportedEncodingException e) {
						sb.append(k).append("=").append(value.toString().trim()).append("&");
					}
				}
			}
		}else {
			for(String k : keyArray) {
				value = map.get(k);
				if(value != null && value.toString().trim().length() > 0) {// 参数值为空，则不参与签名
					sb.append(k).append("=").append(value.toString().trim()).append("&");
				}
			}
		}
		if(sb.length() > 0) {
			return sb.deleteCharAt(sb.length() - 1).toString();
		}
		return null;
	}
	
}
