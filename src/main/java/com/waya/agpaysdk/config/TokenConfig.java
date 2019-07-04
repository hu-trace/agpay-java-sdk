package com.waya.agpaysdk.config;

/**
 * <p>换取token的请求配置接口
 * <p>继承{@link HttpConfig}接口
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see HttpConfig
 * @since 1.8
 * @version 1.0
 * @time 2019年7月3日
 */
public interface TokenConfig {
	
	/**
	 * <p>获取定义的apiId
	 * @return apiId
	 */
	String apiId();
	
	/**
	 * <p>获取定义的apiSecret
	 * @return apiSecret
	 */
	String apiSecret();
	
}
