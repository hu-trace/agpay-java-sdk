package com.waya.agpaysdk.exception;

/**
 * <p>响应中的异常
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see Exception
 * @since 1.8
 * @version 1.0
 * @time 2019年7月3日
 */
public class HttpResponseException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public HttpResponseException(String msg) {
		super(msg);
	}
	
	public HttpResponseException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
