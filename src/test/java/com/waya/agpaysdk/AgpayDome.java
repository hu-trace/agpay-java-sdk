package com.waya.agpaysdk;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.waya.agpaysdk.config.DefaultTokenConfig;
import com.waya.agpaysdk.exception.HttpRequestException;
import com.waya.agpaysdk.exception.HttpResponseException;
import com.waya.agpaysdk.module.HttpModule;
import com.waya.agpaysdk.task.TokenTask;
import com.waya.agpaysdk.util.Utils;


/**
 * Unit test for simple App.
 */
public class AgpayDome {
	
	public static void main(String[] args) throws Exception {
		init();
//		System.out.println(wxorder());
//		System.out.println(aliorder());
//		System.out.println(orderQueryOne("1562203035863537214938294284"));
//		System.out.println(orderRefund("1562205027218421740781326484"));
//		checkNotifySign();
		System.exit(0);
	}
	
	/**
	 * <p>此方法在程序执行过程中只运行一次即可, 多次运行不会做任何事情
	 * @throws HttpRequestException
	 * @throws HttpResponseException
	 */
	public static void init() throws HttpRequestException, HttpResponseException {
		DefaultTokenConfig.build("Yd3rSG7ync", "1b7a48935d71b27dbc74a03e6b98d780");
		TokenTask.start();
	}
	
	/**
	 * 返回数据说明
	 * <pre>
	 * 判断code为0(请求成功)后
	 * 使用{@link JSONObject#getJSONObject(String)}(String为"data")获取有效数据
	 * data: {
	 *   codeUrl: 二维码地址, 使用此地址生成二维码即可
	 *   tradeNo: 交易订单号
	 *   randomStr: 随机字符串
	 * }
	 */
	public static JSONObject wxorder() throws HttpRequestException, HttpResponseException {
		HttpModule module = HttpClient.buildWxpayOrder();
		HashMap<String, Object> param = new HashMap<>();
		param.put("fee", 1);// 交易金额, 单位为分, int类型
		param.put("body", "测试支付");// 交易说明, 用于在支付时展示
		param.put("notifyUrl", "http://pay.wayakeji.com/api/testnotify");// 该订单支付成功后的消息通知地址, 此处地址为测试地址
		module.parameter(param);
		return module.execute();
	}
	
	/**
	 * 返回数据说明
	 * <pre>
	 * 判断code为0(请求成功)后
	 * 使用{@link JSONObject#getJSONObject(String)}(String为"data")获取有效数据
	 * data: {
	 *   codeUrl: 二维码地址, 使用此地址生成二维码即可
	 *   tradeNo: 交易订单号
	 *   randomStr: 随机字符串
	 * }
	 */
	public static JSONObject aliorder() throws HttpRequestException, HttpResponseException {
		HttpModule module = HttpClient.buildAlipayOrder();
		HashMap<String, Object> param = new HashMap<>();
		param.put("fee", 1);// 交易金额, 单位为分, int类型
		param.put("body", "测试支付");// 交易说明, 用于在支付时展示
		param.put("notifyUrl", "http://pay.wayakeji.com/api/testnotify");// 该订单支付成功后的消息通知地址, 此处地址为测试地址
		module.parameter(param);
		return module.execute();
	}
	
	/**
	 * @param tradeNo 交易订单号, 在创建交易订单时生成的
	 * 返回数据说明
	 * <pre>
	 * 判断code为0(请求成功)后
	 * 使用{@link JSONObject#getJSONObject(String)}(String为"data")获取有效数据
	 * data: {
	 *   randomStr: 随机字符串
	 *   clientId: 客户端ID
	 *   tradeNo: 交易订单号
	 *   createTime: 订单创建时间
	 *   fee: 交易金额
	 *   notifyUrl: 交易成功后消息通知地址
	 *   source: 数字(1.微信支付, 2.支付宝支付)
	 *   body: 交易说明
	 *   detail: 交易详情
	 *   status: 交易状态(0.成功, 1.失败, 2.进行中, 3.其它及已过时),
	 *   refunds: 数组, 如果有退款信息则有此字段, 详情见文档
	 * }
	 */
	public static JSONObject orderQueryOne(String tradeNo) throws HttpRequestException, HttpResponseException {
		HttpModule module = HttpClient.buildOrderQueryOne();
		HashMap<String, Object> param = new HashMap<>();
		param.put("tradeNo", tradeNo);// 订单号, 调用aliorder与wxorder后会生成
		module.parameter(param);
		return module.execute();
	}
	
	/**
	 * @param tradeNo 交易订单号, 在创建交易订单时生成的
	 * 返回数据说明
	 * <pre>
	 * 判断code为0(请求成功)后
	 * 使用{@link JSONObject#getJSONObject(String)}(String为"data")获取有效数据
	 * data: {
	 *   refundStatus: 退款状态(0.成功, 1.失败)
	 * }
	 */
	public static JSONObject orderRefund(String tradeNo) throws HttpRequestException, HttpResponseException {
		HttpModule module = HttpClient.buildOrderRefund();
		HashMap<String, Object> param = new HashMap<>();
		param.put("tradeNo", tradeNo);// 订单号, 调用aliorder与wxorder后会生成
		param.put("totalFee", 1);// 订单总金额, 单位为分, int类型
		param.put("refundFee", 1);// 需要退款的金额, 单位为分, int类型
		module.parameter(param);
		return module.execute();
	}
	
	/**
	 * <p>验证签名作用在于notify接口外泄, 防止被人恶意请求notify接口而产生的, 为每条订单产生一个随机字符串进行签名, 保证每条订单的安全性
	 * <p>如果验证不匹配, 则什么都不做, 此处应该是被人恶意请求notify了, 应当做好日志记录
	 * @throws Exception
	 */
	public static void checkNotifySign() throws Exception {
		// 创建交易订单时生成的
		String randomStr = "AS8RCPC2VT5Y5fubcrGRBdXQMKs";
		// 测试字符串
		String str = "{\"tradeNo\":\"1562159718851597168385209647\","
				+ "\"createTime\":\"2019-06-22 09:08:19\","
				+ "\"custom\":\"\",\"fee\":1,\"sign\":\"8B0ABBE822B968FB5E848C7D22BCDFFB\","
				+ "\"paySource\":\"alipay\",\"detail\":\"\",\"body\":\"测试支付\",\"status\":0}";
		JSONObject json = JSONObject.parseObject(str);
		String oldSign = json.getString("sign");
		String nowSign = Utils.notifySign(json, randomStr);
		System.out.println(oldSign);
		System.out.println(nowSign);
		System.out.println(oldSign.equals(nowSign));
	}
	
}