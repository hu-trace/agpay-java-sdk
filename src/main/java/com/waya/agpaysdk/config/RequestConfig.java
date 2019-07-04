package com.waya.agpaysdk.config;

/**
 * <p>正常接口的请求配置接口
 * <p>继承{@link HttpConfig}接口
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see HttpConfig
 * @since 1.8
 * @version 1.0
 * @time 2019年7月3日
 */
public interface RequestConfig {
	
	/**
	 * <p>获取调用者的clientId
	 * @return 调用者的clientId
	 */
	Long clientId();
	
	/**
	 * <p>获取调用者的token
	 * @return 调用者的token
	 */
	String token();
	
	/**
	 * <p>获取调用者的scpsatSecretKey
	 * @return 调用者的scpsatSecretKey
	 */
	String scpsatSecretKey();
	
	/**
	 * <p>获取调用者的sessionChar
	 * @return 调用者的sessionChar
	 */
	String sessionChar();
	
}
