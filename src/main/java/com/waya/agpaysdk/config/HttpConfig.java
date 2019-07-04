package com.waya.agpaysdk.config;

/**
 * <p>Http请求的配置接口
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @since 1.8
 * @version 1.0
 * @time 2019年7月3日
 */
public class HttpConfig {
	
	private final String domain = "http://pay.wayakeji.com";
	
	/**
	 * <p>获取请求的域名
	 * @return 请求域名
	 */
	public String domain() {
		return domain;
	}
	
}
