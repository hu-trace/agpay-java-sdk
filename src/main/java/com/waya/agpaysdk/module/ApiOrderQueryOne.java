package com.waya.agpaysdk.module;


/**
 * <p>根据订单号查询单条订单接口
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see AbstractHttpModule
 * @since 1.8
 * @version 1.0
 * @time 2019年7月3日
 */
public class ApiOrderQueryOne extends AbstractHttpModule {
	
	private static String method = "get";
	private static String module = "/api/order";
	
	@Override
	String module() {
		return module;
	}
	
	@Override
	String method() {
		return method;
	}
	
}
