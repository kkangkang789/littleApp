package com.app.base.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.base.exception.UtilException;
import com.app.base.type.SysType;



/**
 ************************************************** 
 * 字符串工具类
 * 
 * @version 1.0
 * @author chc
 ************************************************** 
 */
public class StringUtil {

	/**
	 * 日志 记录
	 */
	private static final Logger LOG = LoggerFactory.getLogger(StringUtil.class);

	/**
	 * 私有构造
	 */
	private StringUtil() {
	}

	/**
	 * 判断字符串是否包含
	 * 
	 * @param str
	 *            字符串
	 * @param rangeStr
	 *            匹配范围 用/分割的范围
	 * @return 是否匹配上
	 */
	public static boolean isContain(String str, String rangeStr) {
		return isContain(str, rangeStr, SysType.Char.LEFT_LINE.getCharStr());
	}
	
	/**
	 * 判断字符串是否包含
	 * 
	 * @param str
	 *            字符串
	 * @param rangeStr
	 *            匹配范围 用/分割的范围
	 * @return 是否匹配上
	 */
	public static boolean isContain(String str, String rangeStr, String sep) {
		return (sep + rangeStr + sep) .indexOf(sep + str + sep) > -1;
	}

	/**
	 * 字符list转换为字符串
	 * @param con
	 * @param sep
	 * @return
	 * @since 1.0
	 * @author chc 2014-10-20 创建方法
	 */
	public static String getStr(Collection<String> con, String sep) {
		if (BaseUtil.listNull(con)) {
			return "";
		}

		StringBuilder sq = new StringBuilder();
		int i = 0;
		for (String str : con) {
			sq.append(str);
			if (i != con.size() - 1) {
				sq.append(sep);
			}
			i++;
		}

		return sq.toString();
	}

	/**
	 * 判断字符串是否包含
	 * 
	 * @param rangStr
	 *            匹配范围 用/分割的范围
	 * @param rangeStrs
	 *            匹配范围 用/分割的范围
	 * @return 是否匹配上
	 */
	public static boolean isAllContain(String rangStr, String rangeStrs) {
		String[] arrays = rangStr.split(SysType.Char.LEFT_LINE.getCharStr());
		int t = 0;
		for (int i = 0; i < arrays.length; i++) {
			if (isContain(arrays[i], rangeStrs)) {
				t++;
			}
		}
		/**可以为空**/
		if (t == arrays.length || arrays.length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 字符串不为空
	 * 
	 * @param string
	 *            字符串
	 * @return 不为空返回 true 
	 * @since 1.0
	 * @author chc 2014-7-9 创建方法
	 */
	public static boolean stringNotNull(String string) {
		return null != string && !"".equals(string);
	}

	/**
	 * 字符串为空
	 * @param string 字符串
	 * @return  为空返回 true 
	 * @since 1.0
	 * @author chc 2014-7-21 创建方法
	 */
	public static boolean stringNull(String string) {
		return !stringNotNull(string);
	}

	/**
	 * 
	 * @param str
	 * @return
	 * @since 1.0
	 * @author dczhao 2015-3-24 创建方法
	 */
	public static String trim(String str) {
		if (BaseUtil.stringNotNull(str)) {
			return str.trim();
		}
		return str;
	}

	/**
	 * 字符串占位符替换
	 * 
	 * @param pattern
	 *            如:订单{0}状态是{1}
	 * @param params
	 *            如:T2013101098765,支付成功
	 * @return 如: 订单T2013101098765状态是支付成功
	 * @since 3.2.2
	 * @author chc 2013-10-10 创建方法
	 */
	public static String messageFormat(String pattern, Object... params) {
		String formatString = pattern;
		if (stringNotNull(pattern) && BaseUtil.arrayNotNull(params)) {
			formatString = MessageFormat.format(pattern, params);
		}
		return formatString;
	}

	/**
	 * 字符串拼接
	 * 
	 * @param strings
	 *            传入的多个字符串参数
	 * @return 拼接后的字符串
	 * @since 3.2.2
	 * @author chc 2013-10-25 创建方法
	 */
	public static String stringConcat(String... strings) {
		if (!BaseUtil.arrayNotNull(strings)) {
			return "";
		}
		StringBuilder sb = new StringBuilder("");
		for (String string : strings) {
			sb.append(string);
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @param cc
	 * @return
	 * @since 3.0.0
	 * @author chc 2016-3-30 创建方法
	 */
	public static String stringConcat2(String cc, Object... a) {
		String ccs = cc;
		if (ccs == null) {
			ccs = SysType.Char.HORIZ_LINE.getCharStr();
		}
		StringBuilder sb = new StringBuilder();
		if (BaseUtil.arrayNotNull(a)) {
			for (int i = 0; i < a.length; i++) {
				Object object = a[i];
				sb.append(object);
				if (i < a.length - 1) {
					sb.append(ccs);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 将首字母变为大写
	 * 
	 * @param str 目标字符串
	 * @return 转换后的字符串
	 * @since 3.2.2
	 * @author chc 2013-12-5 创建方法
	 */
	public static String capitalize(String str) {
		int strLen;
		if (stringNull(str)) {
			return str;
		}
		strLen = str.length();
		return new StringBuilder(strLen)
				.append(Character.toTitleCase(str.charAt(0)))
				.append(str.substring(1)).toString();
	}

	/**
	 * 字符串的相互包含
	 * @param strone 
	 * @param strother 
	 * @return boolean
	 * @since 1.0
	 * @author gjun 2014-7-31 创建方法
	 */
	public static boolean containEachOther(String strone, String strother) {
		if (stringNull(strone) || stringNull(strother)) {
			return false;
		}
		return strone.contains(strother) || strother.contains(strone);
	}

	/**
	 * 将数组按照分隔符转成字符串
	 * @param strs 数组
	 * @param charsplit charsplit
	 * @return 字符串
	 * @since 1.0
	 * @author gjun 2014-7-31 创建方法
	 */
	public static String join(List<?> strs, String charsplit) {
		StringBuilder sbuild = new StringBuilder();
		if (BaseUtil.objectNotNull(strs)) {
			for (int i = 0, len = strs.size(); i < len; i++) {
				sbuild.append(strs.get(i).toString());
				if (i < len - 1) {
					sbuild.append(charsplit);
				}
			}
		}
		return sbuild.toString();
	}

	/**
	 * 首字母大写
	 * @param str 字符串
	 * @return 首字母大写 的字符串
	 * @since 1.0
	 * @author gjun 2014-8-1 创建方法
	 */
	public static String toUpperFirst(String str) {
		if (stringNull(str)) {
			return SysType.Char.NULL.getCharStr();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(str.substring(0, 1).toUpperCase());
		sb.append(str.substring(1));
		return sb.toString();
	}

	/**
	 * 正则匹配，并返回匹配到的内容
	 * @param regex 正则
	 * @param str 被匹配的内容
	 * @return 匹配到的内容
	 * @since 1.0
	 * @author chc 2014-8-4 创建方法
	 */
	public static String matchStr(String regex, String str) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		boolean flag = m.find();
		String result = null;
		if (flag) {
			result = m.group();
		}
		return result;
	}
	
	/**
	 * 正则匹配，并返回匹配到的内容，可能有多条 
	 * @param regex 正则
	 * @param str 被匹配的内容
	 * @return 匹配到的内容
	 * @since 4.0.0
	 * @author kyang 2016-11-1 创建方法
	 */
	public static List<String> matchStrReturnList(String regex, String str, int group) {
		
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		List<String> result = new ArrayList<String>();
		while(m.find()) {
			result.add(m.group(group));
		}
		
		return result;
	}
	
	/**
	 * 正则匹配，是否匹配到了
	 * @param regex 正则
	 * @param str 被匹配的内容
	 * @return 匹配到的内容
	 * @since 1.0
	 * @author chc 2014-8-4 创建方法
	 */
	public static boolean isMatch(String regex, String str) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		return m.find();
	}

	/**
	 * 自动转换成大写
	 * @param src
	 * @return
	 * @since 1.0
	 * @author dczhao 2015-8-27 创建方法
	 */
	public static String toUpperCase(String src) {
		if (BaseUtil.stringNotNull(src)) {
			return src.toUpperCase();
		}
		return src;
	}

	/**
	 * 将k1=v1,k2=v2,k3=v3的字符放到Map中
	 * @param valueStr k1=v1,k2=v2,k3=v3
	 * @return map
	 * @since 1.0
	 * @author chc 2014-8-4 创建方法
	 */
	public static Map<String, String> getKeyValue(String valueStr) {
		return getKeyValue(valueStr, ",");
	}

	/**
	 * 将k1=v1,k2=v2,k3=v3的字符放到Map中
	 * @param valueStr  k1=v1,k2=v2,k3=v3
	 * @param sp 分隔符
	 * @return map
	 * @since 1.0
	 * @author chc 2014-8-8 创建方法
	 */
	public static Map<String, String> getKeyValue(String valueStr, String sp) {
		String values = valueStr.trim();
		String[] oneValues = values.split(sp);
		Map<String, String> map = new HashMap<String, String>();
		for (String oneValue : oneValues) {
			String[] ones = oneValue.split("=");
			String key = ones[0].trim();
			String value = null;
			if (ones.length == 2) {
				value = ones[1].trim();
			}

			if (BaseUtil.stringNotNull(value) && SysType.Char.NULL_CHAR
					.getCharStr().equalsIgnoreCase(value)) {
				value = SysType.Char.NULL.getCharStr();
			}

			map.put(key, value);
		}
		return map;
	}

	/**
	 * 两个字符串是否相交
	 * @param str1 字符串一 如：A/B/C/D
	 * @param str2 字符串二 如：D/E/B
	 * @return true/false ,如：true
	 * @since 1.0
	 * @author chc 2014-8-5 创建方法
	 */
	public static boolean isIntersect(String str1, String str2) {
		return isIntersect(str1, str2, SysType.Char.LEFT_LINE.getCharStr());
	}

	/**
	 * 两个字符串是否相交
	 * @param str1 字符串一 如：A/B/C/D
	 * @param str2 字符串二 如：D/E/B
	 * @param seq 分隔符  如：/
	 * @return true/false ,如：true
	 * @since 1.0
	 * @author chc 2014-8-5 创建方法
	 */
	public static boolean isIntersect(String str1, String str2, String sep) {
		if (BaseUtil.stringNull(str1) || BaseUtil.stringNull(str2)) {
			return false;
		}

		String[] arrays = str2.split(sep);

		for (int i = 0; i < arrays.length; i++) {
			String str = arrays[i];
			if (isContain(str, str1)) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * 将boolean转换为string
	 * @param flag true,false
	 * @return true,false
	 * @since 1.0
	 * @author chc 2014-8-7 创建方法
	 */
	public static String booleanToString(boolean flag) {
		if (flag) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * 根据sep切分字符串，并返回list
	 * @param str 字符串
	 * @param sep 分隔符，默认为 左斜线  /
	 * @return
	 * @since 1.0
	 * @author chc 2014-8-27 创建方法
	 */
	public static List<String> split(String str, String sep) {
		List<String> temp = null;
		String tempSep = sep;
		if (BaseUtil.objectNull(tempSep)) {
			tempSep = SysType.Char.LEFT_LINE.getCharStr();
		}
		if (BaseUtil.stringNotNull(str)) {
			if(BaseUtil.stringNull(sep)) {
				temp = new ArrayList<String>();
				for(int i = 0; i < str.length(); i++) {
					temp.add(String.valueOf(str.charAt(i)));
				}
			} else {
				String[] strs = str.split(tempSep);
				temp = Arrays.asList(strs);
			}
		}

		return temp;
	}

	/**
	 * 将字符转换为BigDecimal
	 * @param num 字符数字
	 * @return BigDecimal
	 * @since 1.0
	 * @author chc 2014-9-30 创建方法
	 */
	public static BigDecimal toBigDecimal(String num) {
		if (BaseUtil.stringNull(num)) {
			return null;
		}
		return new BigDecimal(num);
	}

	/**
	 * base64压缩编码
	 * @param tocompress
	 * @return
	 * @since 2.1.1
	 * @author chc 2015-6-29 创建方法
	 */
	public static String compressGzipB64(String tocompress, String encoding) {
		if (!BaseUtil.stringNotNull(tocompress)) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = null;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(tocompress.getBytes(encoding));
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			try {
				if (null != gzip) {
					gzip.flush();
					gzip.close();
				}
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
		}
		return new Base64().encodeToString(out.toByteArray());
	}

	/**
	 * 解压缩
	 * @param compressed
	 * @return
	 * @throws IOException
	 * @since 5.0
	 * @author mfw 2015-3-20 创建方法
	 */
	public static String uncompressGzipB64(String compressed, String encoding) {
		if (!BaseUtil.stringNotNull(compressed)) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] c = new Base64().decode(compressed);
		ByteArrayInputStream in = new ByteArrayInputStream(c);
		GZIPInputStream gunzip = null;
		try {
			gunzip = new GZIPInputStream(in);
			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = gunzip.read(buffer)) >= 0) {
				out.write(buffer, 0, offset);
			}
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			try {
				if (null != out) {
					out.close();
				}
				if (null != in) {
					in.close();
				}
				if (null != gunzip) {
					gunzip.close();
				}
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
		}
		try {
			return out.toString(encoding);
		} catch (UnsupportedEncodingException e) {
			throw new UtilException(e);
		}
	}
}
