package com.waya.agpaysdk.config;

/**
 * <p>默认一般请求的配置信息
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see RequestConfig
 * @see HttpConfig
 * @since 1.8
 * @version 1.0
 * @time 2019年7月3日
 */
public class DefaultRequestConfig extends HttpConfig implements RequestConfig {
	
	public static final DefaultRequestConfig instance = new DefaultRequestConfig();
	
	private Long clientId;
	private String token;
	private String scpsatSecretKey;
	private String sessionChar;
	
	private DefaultRequestConfig() {}
	
	@Override
	public Long clientId() {
		return clientId;
	}

	@Override
	public String token() {
		return token;
	}

	@Override
	public String scpsatSecretKey() {
		return scpsatSecretKey;
	}

	@Override
	public String sessionChar() {
		return sessionChar;
	}
	
	/**
	 * <p>构建配置信息
	 * @param clientId
	 * @param token
	 * @param scpsatSecretKey
	 * @param sessionChar
	 * @return
	 */
	public static DefaultRequestConfig build(Long clientId, String token, String scpsatSecretKey, String sessionChar) {
		instance.clientId = clientId;
		instance.token = token;
		instance.scpsatSecretKey = scpsatSecretKey;
		instance.sessionChar = sessionChar;
		return instance;
	}
	
}
