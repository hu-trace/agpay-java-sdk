package com.waya.agpaysdk.module;


/**
 * <p>支付宝支付创建订单接口
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see AbstractHttpModule
 * @since 1.8
 * @version 1.0
 * @time 2019年7月3日
 */
public class ApiOrderAlipay extends AbstractHttpModule {
	
	private static String method = "post";
	private static String module = "/api/aliorder";
	
	@Override
	String module() {
		return module;
	}
	
	@Override
	String method() {
		return method;
	}
	
}
