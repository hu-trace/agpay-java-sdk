package com.waya.agpaysdk.config;

/**
 * <p>默认换取token的配置信息
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see TokenConfig
 * @see HttpConfig
 * @since 1.8
 * @version 1.0
 * @time 2019年7月3日
 */
public class DefaultTokenConfig extends HttpConfig implements TokenConfig {
	
	public static final DefaultTokenConfig instance = new DefaultTokenConfig();
	
	private String apiId;
	private String apiSecret;
	
	private DefaultTokenConfig() {}

	@Override
	public String apiId() {
		return apiId;
	}

	@Override
	public String apiSecret() {
		return apiSecret;
	}
	
	/**
	 * <p>构造Config, 使用调用者的apiId与apiSecret进行构建
	 * @param apiId
	 * @param apiSecret
	 */
	public static DefaultTokenConfig build(String apiId, String apiSecret) {
		instance.apiId = apiId;
		instance.apiSecret = apiSecret;
		return instance;
	}
	
}
