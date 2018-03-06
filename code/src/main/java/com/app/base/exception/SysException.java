/**
 * SysException.java
 * 作者：杨成科  2014-2-13 创建文件
 * copyright 重庆分销研发部运价组
 */
package com.app.base.exception;


/**
 **************************************************
 * 系统异常定义类 
 *  
 * @since 1.0                                   
 * @author  Ckyang                                                                 
 *************************************************
 */
public class SysException extends RuntimeException {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -8351102467024637046L;

	/**
	 * 系统消息代码
	 */
	private final Integer sysCode;

	/**
	 * 系统消息
	 */
	private final String sysMsg;

	/**
	 * 只含系统消息代码构造函数
	 * @param sysCode 系统消息代码
	 */
	public SysException(Integer sysCode) {
		this(sysCode, null, null);
	}

	/**
	 * 只含系统消息构造函数
	 * @param sysMsg 系统消息
	 */
	public SysException(String sysMsg) {
		this(null, sysMsg, null);
	}

	/**
	 * 包含系统消息代码，系统消息的构造函数
	 * @param sysCode 系统消息代码
	 * @param sysMsg 系统消息
	 */
	public SysException(Integer sysCode, String sysMsg) {
		this(sysCode, sysMsg, null);
	}

	/**
	 * 只含Throwable的构造函数
	 * @param e Throwable
	 */
	public SysException(Throwable e) {
		this(null, e.getMessage(), e);
	}

	/**
	 *  包含系统消息代码，系统消息，Throwable的构造函数
	 * @param sysCode 系统消息代码
	 * @param sysMsg 系统消息
	 * @param e Throwable
	 */
	public SysException(Integer sysCode, String sysMsg, Throwable e) {
		super(sysMsg, e);
		this.sysCode = sysCode;
		this.sysMsg = sysMsg;
	}
	
	/**
	 *  包含系统消息代码，系统消息，Throwable的构造函数
	 * @param sysCode 系统消息代码
	 * @param sysMsg 系统消息
	 * @param e Throwable
	 */
	public SysException(String sysMsg, Throwable e) {
		super(sysMsg, e);
		this.sysCode = 500;
		this.sysMsg = sysMsg;
	}


	/**
	 * 取得sysCode
	 * @return the sysCode 
	 */
	public Integer getSysCode() {
		return sysCode;
	}

	/**
	 * 取得sysMsg
	 * @return the sysMsg 
	 */
	public String getSysMsg() {
		return sysMsg;
	}
}
