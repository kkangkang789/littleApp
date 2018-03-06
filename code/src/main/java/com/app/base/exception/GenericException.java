/**  
 * @Copyright ginkgo
 */

package com.app.base.exception;

/**
 * 
 * 基础异常，其他异常需继承此异常，以保证前端页面获得的json格式能够保持统一
 *
 * @author iccboy
 * @date 2015-5-21
 */
public class GenericException extends RuntimeException {
	private static final long serialVersionUID = 0L;

	private String code; // 错误代码
	private Object data; // 实际数据

	public GenericException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public GenericException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public GenericException(String message, Throwable cause) {
		super(message, cause);
		this.code = message;
	}

	/**
	 * @param cause
	 */
	public GenericException(Throwable cause) {
		super(cause);
		this.code = cause.getMessage();
	}

	public GenericException(String code) {
		this.code = code;
		this.data = null;
	}

	public GenericException(String code, Object data) {
		this.code = code;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
