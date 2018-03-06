/**
 * BaseUtil.java
 * 作者：赵韩列
 * 2016-11-5 创建文件
 */
package com.app.base.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.app.base.exception.GenericException;


/**
 * 
 **************************************************
 * 基础工具类
 * 
 * @version 1.0.0
 * @author  hlzhao
 * @date 2016年11月5日
 **************************************************
 */
public class BaseUtil {

	/**
	 * 私有构造
	 */
	private BaseUtil() {
	}

	/**
	 * 通配符
	 */
	private static final String LIKESTR = ".*";
	
	/**
	 * 获取访问请求的IP地址
	 * @param request
	 * @return
	 * @since 1.0.0
	 * @author hlzhao 2017年8月11日 创建方法
	 */
	public static String getIpAddr(HttpServletRequest request) {  
        String ip = request.getHeader("X-Forwarded-For");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
    }

	/**
	 * 计算采用utf-8编码方式时字符串所占字节数
	 * 
	 * @param content 字符串
	 * @return 字符串所占字节数
	 */
	public static int getByteSize(String content) {
		int size = 0;
		if (null != content) {
			try {
				// 汉字采用utf-8编码时占3个字节
				size = content.getBytes("UTF-8").length;
			} catch (UnsupportedEncodingException e) {
				throw new GenericException("EC_UTIL_EXCEPTION", e);
			}
		}
		return size;
	}

	/**
	 * 判断集合是否不为空
	 * @param con 集合
	 * @return 不为空 返回 true
	 */
	public static boolean listNotNull(Collection<?> con) {
		return con != null && !con.isEmpty();
	}

	/**
	 * 判断集合是否不为空
	 * @param con 集合
	 * @return 不为空 返回 true
	 */
	public static boolean listNull(Collection<?> con) {
		return !listNotNull(con);
	}

	/**
	 * 判断数组 不为空
	 * @param array
	 * @return
	 * @since 1.0.0
	 * @author hlzhao 2016年11月5日 创建方法
	 */
	public static boolean arrayNotNull(Object[] array) {
		if (array != null) {
			return array.length != 0;
		}
		return false;
	}

	/**
	 * 对象是否不为空
	 * @param obj
	 * @return
	 * @since 1.0.0
	 * @author hlzhao 2016年11月5日 创建方法
	 */
	public static boolean objectNotNull(Object obj) {
		return obj != null;
	}

	/**
	 * 对象是否为空
	 * @param obj
	 * @return
	 * @since 1.0.0
	 * @author hlzhao 2016年11月5日 创建方法
	 */
	public static boolean objectNull(Object obj) {
		return !objectNotNull(obj);
	}

	/**
	 * 判断map是否为空
	 * @param map
	 * @return
	 * @since 1.0.0
	 * @author hlzhao 2016年11月5日 创建方法
	 */
	public static boolean mapNotNull(Map<?, ?> map) {
		return null != map && !map.isEmpty();
	}

	/**
	 * 字符串不为空
	 * @param string
	 * @return
	 * @since 1.0.0
	 * @author hlzhao 2016年11月5日 创建方法
	 */
	public static boolean stringNotNull(String string) {
		return null != string && !"".equals(string);
	}

	/**
	 * 字符串为空
	 * @param string
	 * @return
	 * @since 1.0.0
	 * @author hlzhao 2016年11月5日 创建方法
	 */
	public static boolean stringNull(String string) {
		return !stringNotNull(string);
	}

	/**
	 * 单一字符串匹配
	 * @param regex
	 * @param value
	 * @return
	 * @since 1.0.0
	 * @author hlzhao 2016年11月5日 创建方法
	 */
	public static boolean regexStrSimple(String regex, String value) {
		Pattern patter = Pattern.compile(regex);
		Matcher mat = patter.matcher(value);
		return mat.matches();
	}

	/**
	 * 集合任意字符匹配 any
	 * @param regex
	 * @param values
	 * @return
	 * @since 1.0.0
	 * @author hlzhao 2016年11月5日 创建方法
	 */
	public static Boolean regexStrAnyMatch(String regex, List<String> values) {
		Boolean flag = false;
		if (listNotNull(values)) {
			for (int i = 0, l = values.size(); i < l; i++) {
				if (regexStrSimple(regex, values.get(i))) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 字符串集合全部匹配
	 * @param regex
	 * @param values
	 * @return
	 * @since 1.0.0
	 * @author hlzhao 2016年11月5日 创建方法
	 */
	public static Boolean regexStrAllMatch(String regex, List<String> values) {
		Boolean flag = true;
		if (listNotNull(values)) {
			for (int i = 0, l = values.size(); i < l; i++) {
				if (!regexStrSimple(regex, values.get(i))) {
					flag = false;
					break;
				}
			}
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * 多字符正则匹配单一字符
	 * @param regexStr
	 * @param value
	 * @return
	 * @since 1.0.0
	 * @author hlzhao 2016年11月5日 创建方法
	 */
	public static Boolean regexStringLikes(List<String> regexStr,
			String value) {
		Boolean flag = false;
		if (listNotNull(regexStr)) {
			for (int i = 0, l = regexStr.size(); i < l; i++) {
				if (regexStrSimple(LIKESTR + regexStr.get(i) + LIKESTR,
						value)) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 将首字母变为大写
	 * @param str
	 * @return
	 * @since 1.0.0
	 * @author hlzhao 2016年11月5日 创建方法
	 */
	public static String capitalize(String str) {
		if (BaseUtil.stringNull(str)) {
			return str;
		}
		int strLen = str.length();
		return new StringBuilder(strLen)
				.append(Character.toTitleCase(str.charAt(0)))
				.append(str.substring(1)).toString();
	}

	/**
	 * 深度复制 Model类必须继承 implements Serializable### 通过工具类，如spring的BeanUtils,
	 * @param obj
	 * @return
	 * @since 1.0.0
	 * @author hlzhao 2016年11月5日 创建方法
	 */
	@SuppressWarnings("unchecked")
	public static <T> T copy(T obj) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			ObjectInputStream ois = new ObjectInputStream(
					new ByteArrayInputStream(bos.toByteArray()));
			return (T) ois.readObject();
		} catch (IOException e) {
			throw new GenericException("EC_UTIL_EXCEPTION", e);
		} catch (ClassNotFoundException e) {
			throw new GenericException("EC_UTIL_EXCEPTION", e);
		} catch (RuntimeException e) {
			throw new GenericException("EC_UTIL_EXCEPTION", e);
		}
	}

	/**
	 * 去除list中重复的元素
	 * @param cons
	 * @return
	 * @since 1.0.0
	 * @author hlzhao 2016年11月5日 创建方法
	 */
	public static List<String> removeDuplicate(List<String> cons) {
		if (BaseUtil.listNotNull(cons)) {
			List<String> newList = new ArrayList<String>();
			for (String string : cons) {
				if (!newList.contains(string)
						&& BaseUtil.stringNotNull(string)) {
					newList.add(string);
				}
			}
			return newList;
		} else {
			return cons;
		}
	}

	/**
	 * 判断value在数组ranges中的哪个位置
	 * @param ranges
	 * @param value
	 * @return
	 * @since 1.0.0
	 * @author hlzhao 2016年11月5日 创建方法
	 */
	public static <T> int indexOf(T[] ranges, T value) {
		if (arrayNotNull(ranges)) {
			for (int i = 0; i < ranges.length; i++) {
				T cvalue = ranges[i];
				if (cvalue == value
						|| (cvalue != null && cvalue.equals(value))) {
					return i;
				}
			}
		}

		return -1;
	}
	
	/**
	 * 字符串中出现某字符串的次数
	 * @param str
	 * @param repeatStr
	 * @return
	 * @since 1.0.0
	 * @author hlzhao 2016年11月5日 创建方法
	 */
	public static int repeatStrCount(String str, String repeatStr) {
		
		if(repeatStr.length() > str.length()) {
			return 0;
		}
		if(str.indexOf(repeatStr) > -1) {
			return 1 + repeatStrCount(str.substring(str.indexOf(repeatStr)+1),repeatStr);
		}
		return 0;
	}
	
	/**
	 * 
	 * @Title: getNullPropertyNames 
	 * @Description: 获取非空属性数组
	 * @param source
	 * @return 
	 * @return String[]
	 */
	public static String[] getNullPropertyNames(Object source) {  
        final BeanWrapper src = new BeanWrapperImpl(source);  
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();  
   
        Set<String> emptyNames = new HashSet<String>();  
        for (java.beans.PropertyDescriptor pd : pds) {  
            Object srcValue = src.getPropertyValue(pd.getName());  
            if (srcValue == null)  
                emptyNames.add(pd.getName());  
        }  
        String[] result = new String[emptyNames.size()];  
        return emptyNames.toArray(result);
    }  
	
	/**
	 * 
	 * @Title: copyPropertiesIgnoreNull 
	 * @Description: copy非空属性对象
	 * @param source
	 * @param target 
	 * @return void
	 */
	public static void copyPropertiesIgnoreNull(Object source,Object target){
		BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
	}
}
