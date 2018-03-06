/**
 * AppException.java
 * 作者：杨成科  2014-2-13 创建文件
 * copyright 重庆分销研发部运价组
 */
package com.app.base.exception;


/**
 **************************************************
 * 业务异常定义类
 *  
 * @since 1.0                                   
 * @author  Ckyang                                                                 
 *************************************************
 */
public class UtilException extends RuntimeException {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1931617223087270669L;

	/**
	 * 应用消息代码
	 */
	private final Integer utilCode;

	/**
	 * 应用消息
	 */
	private final String utilMsg;

	/**
	 * 只含应用消息代码构造函数
	 * @param appCode 应用消息代码
	 */
	public UtilException(Integer utilCode) {
		this(utilCode, null, null);
	}

	/**
	 * 只含应用消息构造函数
	 * @param appMsg 应用消息
	 */
	public UtilException(String utilMsg) {
		this(null, utilMsg, null);
	}

	/**
	 * 包含应用消息代码,应用消息的构造函数
	 * @param appCode 应用消息代码
	 * @param appMsg 应用消息
	 */
	public UtilException(Integer utilCode, String utilMsg) {
		this(utilCode, utilMsg, null);
	}


	/**
	 * 包含Throwable的构造函数
	 * @param e Throwable
	 */
	public UtilException(Throwable e) {
		this(null, null, e);
	}

	/**
	 * 包含应用消息代码,应用消息,Throwable的构造函数
	 * @param appCode 应用消息代码
	 * @param appMsg 应用消息
	 * @param e Throwable
	 */
	public UtilException(Integer utilCode, String utilMsg, Throwable e) {
		super(utilMsg, e);
		this.utilCode = utilCode;
		this.utilMsg = utilMsg;
	}

	/**
	 * 取得utilCode
	 * @return the utilCode 
	 */
	public Integer getUtilCode() {
		return utilCode;
	}

	/**
	 * 取得utilMsg
	 * @return the utilMsg 
	 */
	public String getUtilMsg() {
		return utilMsg;
	}
}
