package com.app.base.type;

/**
 **************************************************
 * 类型定义枚举
 * 
 * @version 1.0
 * @author  chc
 * @date 2016-5-24                                                                 
 **************************************************
 */
public class SysType {

	/**
	 **************************************************
	 * 字符集常量类
	 * 
	 * @version 1.0
	 * @author  chc
	 * @date 2016-5-24
	 **************************************************
	 */
	public enum Char {

		/**
		 * 换行
		 */
		LINE(System.getProperty("line.separator")),

		/**
		 *\/
		 */
		LEFT_LINE("/"),

		/**
		 * %
		 */
		PERCENT("%"),

		/**
		 * 空格
		 */
		SPACE(" "),

		/**
		 * -
		 */
		HORIZ_LINE("-"),

		/**
		 * 空字符
		 */
		NULL(""),

		/**
		 * 等号 
		 */
		EQUAL("="),

		/**
		 * null字符
		 */
		NULL_CHAR("null"),

		/**
		 * 逗号，
		 */
		COMMA(","),
		//该符号不能删除
		/**
		 * .
		 */
		POINT("."),

		/**
		 *\//
		 */
		DOUBLELEFTLINE("//"),
		/**
		 * ?
		 */
		WHYCHAR("?"),
		/**
		 * &
		 */
		ANDCHAR("&"),

		/**
		 * 竖线 \\|
		 */
		VERTICALCHAR("\\|"),

		/**
		 * # 
		 */
		SHARPCHAR("#")

		;

		/**
		 * 字符
		 */
		private String charStr;

		/**
		 * 构造方法
		 * @param charStr 字符
		 */
		private Char(String charStr) {
			this.charStr = charStr;
		}

		/**
		 * 取得charStr
		 * @return the charStr 
		 */
		public String getCharStr() {
			return charStr;
		}

		/**
		 * 设置charStr
		 * @param charStr the charStr to set
		 */
		public void setCharStr(String charStr) {
			this.charStr = charStr;
		}
	}

	/**
	 * 
	 **************************************************
	 * 编码类型枚举
	 * 
	 * @version 1.0
	 * @author  chc
	 * @date 2016-5-24                                                                 
	 **************************************************
	 */
	public enum Encoding {

		/**
		 * UTF-8
		 */
		UTF8("UTF-8"),

		/**
		 *GBK
		 */
		GBK("GBK"),
		/**
		 * ISO
		 */
		ISO("ISO-8859-1"),
		//该符号不能删除
		;

		/**
		 * 字符
		 */
		private String code;

		/**
		 * 构造方法
		 * @param code 字符
		 */
		private Encoding(String code) {
			this.code = code;
		}

		/**
		 * 取得code
		 * @return the code 
		 */
		public String getCode() {
			return code;
		}

		/**
		 * 设置code
		 * @param code the code to set
		 */
		public void setCode(String code) {
			this.code = code;
		}

	}

	/**
	 **************************************************
	 * 分页参数枚举
	 * 
	 * @version 1.0
	 * @author  chc
	 * @date 2016-5-24                                                                 
	 **************************************************
	 */
	public enum Pager {

		/**
		 * 当前页
		 */
		DELFAULT_CURRENT_PAGE_NUM(1),

		/**
		 * 没有大小
		 */
		DELFAULT_PER_PAGE_SIZE(10)

		;

		/**
		 * 数字
		 */
		private Integer num;

		/**
		 * 构造方法
		 * @param num 数字
		 */
		private Pager(Integer num) {
			this.num = num;
		}

		/**
		 * 取得num
		 * @return the num 
		 */
		public Integer getNum() {
			return num;
		}

		/**
		 * 设置num
		 * @param num the num to set
		 */
		public void setNum(Integer num) {
			this.num = num;
		}
	}

	/**
	 * 取得系统异常日志打印提示信息
	 * @return hit
	 * @since 1.0
	 * @author chc 2014-7-22 创建方法
	 */
	public static String getSysExceptionHit() {
		return "【系统异常】";
	}

	/**
	 * 取得系统异常日志打印提示信息
	 * @param code code
	 * @return hit
	 * @since 1.0
	 * @author chc 2014-7-22 创建方法
	 */
	public static String getSysExceptionHit(Integer code) {
		return "【系统异常(" + code + ")】";
	}

	/**
	 * 取得应用异常日志打印提示信息
	 * @return hit
	 * @since 1.0
	 * @author chc 2014-7-22  创建方法
	 */
	public static String getAppExceptionHit() {
		return "【应用异常】";
	}

	/**
	 * 取得应用异常日志打印提示信息
	 * @param code code
	 * @return hit
	 * @since 1.0
	 * @author chc 2014-7-22  创建方法
	 */
	public static String getAppExceptionHit(Integer code) {
		return "【应用异常(" + code + ")】";
	}

}
