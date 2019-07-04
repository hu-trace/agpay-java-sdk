package com.waya.agpaysdk;

import com.waya.agpaysdk.module.ApiOrderAlipay;
import com.waya.agpaysdk.module.ApiOrderQueryOne;
import com.waya.agpaysdk.module.ApiOrderRefund;
import com.waya.agpaysdk.module.ApiOrderWxpay;
import com.waya.agpaysdk.module.HttpModule;

/**
 * <p>WAYA Pay请求客户端, 通过此类可以快速构造请求接口
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see HttpModule
 * @see ApiOrderAlipay
 * @see ApiOrderWxpay
 * @see ApiOrderRefund
 * @see ApiOrderQueryOne
 * @since 1.8
 * @version 1.0
 * @time 2019年7月3日
 */
public class HttpClient {
	
	/**
	 * <p>构造支付宝支持创建订单接口
	 * @return {@link HttpModule}
	 */
	public static HttpModule buildAlipayOrder() {
		return new ApiOrderAlipay();
	}
	
	/**
	 * <p>构造微信支付创建订单接口
	 * @return {@link HttpModule}
	 */
	public static HttpModule buildWxpayOrder() {
		return new ApiOrderWxpay();
	}

	/**
	 * <p>构造订单退款接口
	 * @return {@link HttpModule}
	 */
	public static HttpModule buildOrderRefund() {
		return new ApiOrderRefund();
	}
	
	/**
	 * <p>构造根据订单号查询单条订单接口
	 * @return {@link HttpModule}
	 */
	public static HttpModule buildOrderQueryOne() {
		return new ApiOrderQueryOne();
	}
	
}
