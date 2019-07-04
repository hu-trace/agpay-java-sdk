package com.waya.agpaysdk.task;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import com.alibaba.fastjson.JSONObject;
import com.waya.agpaysdk.config.DefaultRequestConfig;
import com.waya.agpaysdk.config.DefaultTokenConfig;
import com.waya.agpaysdk.exception.HttpRequestException;
import com.waya.agpaysdk.exception.HttpResponseException;
import com.waya.agpaysdk.http.HttpRequest;

/**
 * <p>token任务, 该任务需要在所有接口调用前调用一次即可, 重复调用不会做任何事情
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see Timer
 * @see TimerTask
 * @since 1.8
 * @version 1.0
 * @time 2019年7月3日
 */
public class TokenTask {
	
	private static boolean init = false;
	
	/**
	 * <p>启动token任务, 此方法仅第一次调用生效
	 * @throws HttpRequestException
	 * @throws HttpResponseException
	 */
	public static void start() throws HttpRequestException, HttpResponseException {
		if(!init) {
			init = true;
			TokenTask task = new TokenTask();
			task.getToken();
			task.task();
		}
	}
	
	private void task() {
		long daySpan = 24 * 60 * 60 * 1000;
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DAY_OF_YEAR, 1);
		date.set(Calendar.HOUR_OF_DAY, 1);// 1点
		date.set(Calendar.MINUTE, 1);// 1分
		date.set(Calendar.SECOND, 0);// 0秒
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {
					getToken();
				}catch (HttpRequestException e) {
					e.printStackTrace();
				}catch (HttpResponseException e) {
					e.printStackTrace();
				}
			}
		};
		timer.schedule(task, date.getTime(), daySpan);
	}
	
	private void getToken() throws HttpRequestException, HttpResponseException {
		HttpRequest request = new HttpRequest();
		HashMap<String, Object> parameter = new HashMap<>();
		parameter.put("apiId", DefaultTokenConfig.instance.apiId());
		parameter.put("apiSecret", DefaultTokenConfig.instance.apiSecret());
		JSONObject json = request.get("/api/token", parameter, null);
		if(json.getIntValue("code") != 0) {
			throw new HttpResponseException(json.getString("msg"));
		}
		JSONObject data = json.getJSONObject("data");
		DefaultRequestConfig.build(
				data.getLong("clientId"),
				data.getString("token"),
				data.getString("scpsatSecretKey"),
				data.getString("sessionChar"));
	}
	
}
