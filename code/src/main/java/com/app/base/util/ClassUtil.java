/**
 * ClassUtil.java
 * 作者：陈超
 * 2014-7-21 创建文件
 */
package com.app.base.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.base.exception.SysException;
import com.app.base.exception.UtilException;



/**
 ************************************************** 
 * java反射类
 * 
 * @version 1.0
 * @author chc
 ************************************************** 
 */
public final class ClassUtil {

	/**
	 * LOGGER
	 */
	private static final Logger LOGGER =
			LoggerFactory.getLogger(ClassUtil.class);

	/**
	 * 私有构造
	 */
	private ClassUtil() {
	}

	/**
	 * 获得方法
	 * 
	 * @param obj
	 *            对象
	 * @param mothedName
	 *            方法名
	 * @param params 参数
	 * @return 方法
	 * @since 1.0
	 * @author chc 2014-7-21 创建方法
	 */
	public static Method getMethod(Object obj, String mothedName,
			Object... params) {
		try {
			if (BaseUtil.objectNull(obj)) {
				return null;
			}
			Class<?>[] c = null;
			if (BaseUtil.arrayNotNull(params)) {// 存在
				int len = params.length;
				c = new Class[len];
				for (int i = 0; i < len; ++i) {
					c[i] = params[i].getClass();
				}
			}

			try {
				return obj.getClass().getDeclaredMethod(mothedName, c);
			} catch (NoSuchMethodException e) {
				LOGGER.debug(e.getMessage(), e);
				try {
					return obj.getClass().getMethod(mothedName, c);
				} catch (NoSuchMethodException e1) {
					throw new UtilException(e1);
				}
			}
		} catch (RuntimeException e) {
			throw new UtilException(e);
		}
	}


	/**
	 * 获取类中所有属性(包含父类)
	 * @param clazz 类型
	 * @return 字段
	 * @since 1.0
	 * @author chc 2014-8-18 创建方法
	 */
	public static Field[] getAllFields(Class<?> clazz) {
		List<Field> fieldList = new ArrayList<Field>();
		getFields(fieldList, clazz);
		int size = fieldList.size();
		return (Field[]) fieldList.toArray(new Field[size]);
	}

	/**
	 * 获得属性
	 * @param fieldList 属性list
	 * @param clazz 类型
	 * @since 1.0
	 * @author chc 2014-8-18 创建方法
	 */
	private static void getFields(List<Field> fieldList, Class<?> clazz) {
		if (Object.class.equals(clazz) || clazz == null) {
			return;
		}
		Field[] fields = clazz.getDeclaredFields();//获取本类中的所有属性（private/public/protected/default）
		List<Field> currentFieldList = Arrays.asList(fields);
		fieldList.addAll(currentFieldList);
		getFields(fieldList, clazz.getSuperclass());
	}

	/**
	 * 得到类上泛型的实体类
	 * @param <T> 泛型
	 * @param entity 带泛型的类
	 * @param index 第几个泛型参数
	 * @return 泛型实体
	 * @since 1.0
	 * @author chc 2014-7-13 创建方法
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getEntityClass(Class<T> entity, int index) {
		Class<T> t = null;
		if (null == entity) {
			t = null;
		} else if (Object.class.equals(entity)) {
			t = (Class<T>) Object.class;
		} else {
			ParameterizedType type = getType(entity);
			if (null == type) {
				t = (Class<T>) Object.class;
			} else {
				Type[] types = type.getActualTypeArguments();
				if (null == types || types.length == 0) {
					t = (Class<T>) Object.class;
				} else if (index > -1) {
					t = (Class<T>) (index < types.length ? types[index]
							: types[types.length - 1]);
				} else {
					t = (Class<T>) types[0];
				}
			}
		}
		return t;
	}

	/**
	 * 获得类的泛型
	 * 
	 * @param <T> 泛型
	 * @param entity 类
	 * @return type
	 * @since 1.0
	 * @author chc 2014-7-21 创建方法
	 */
	private static <T> ParameterizedType getType(Class<T> entity) {
		ParameterizedType type = null;
		try {
			type = (ParameterizedType) (entity.getGenericSuperclass());
		} catch (RuntimeException e) {
			LOGGER.debug(e.getMessage(), e);
			if (Object.class.equals(entity)) {
				return null;
			}
			type = getType(entity.getSuperclass());
		}

		return type;
	}

	/**
	 * 得到类上第一个泛型的实体类
	 * @param <T> 实体泛型
	 * @param entity 实体
	 * @return 实体类型
	 * @since 1.0
	 * @author chc 2014-7-13 创建方法
	 */
	public static <T> Class<T> getEntityClass(Class<T> entity) {
		return getEntityClass(entity, 0);
	}

	/**
	 * 获得java对象的属性和值
	 * @param s
	 * @return
	 * @throws SysException
	 * @since 1.0
	 * @author dczhao 2014-8-19 创建方法
	 */
	public static Map<String, Object> getObjectFieldAndValues(Object s) {
		Class<?> clazz = s.getClass();
		Map<String, Object> map = new HashMap<String, Object>();
		Method[] method = clazz.getMethods();
		for (int i = 0; i < method.length; i++) {
			Method m = method[i];
			if (m.getName().startsWith("get")) {
				String filed = m.getName().substring(3);
				String first = filed.substring(0, 1).toLowerCase();
				filed = filed.replaceFirst(filed.substring(0, 1), first);

				try {
					Object ob = m.invoke(s, new Object[0]);
					if (null != ob) {
						map.put(filed, ob);
					}
				} catch (IllegalAccessException e) {
					throw new SysException(e);
				} catch (InvocationTargetException e) {
					throw new SysException(e);
				}

			}
		}
		map.remove("class");
		return map;
	}

	/**
	 * 格式化实体域 将空字符
	 * @param obj
	 * @since 1.0
	 * @author ShuangYang 2014-9-29 创建方法
	 */
	public static void formatEmptyFields(Object obj) {
		Method[] methods = obj.getClass().getMethods();
		Map<String, Object> maps = getObjectFieldAndValues(obj);
		for (Method method : methods) {
			if (method.getName().startsWith("set")) {
				String filed = method.getName().substring(3);
				String first = filed.substring(0, 1).toLowerCase();
				filed = filed.replaceFirst(filed.substring(0, 1), first);
				if ("".equals(maps.get(filed))) {
					try {
						method.invoke(obj, new Object[] { null });
					} catch (IllegalArgumentException e) {
						throw new SysException(e);
					} catch (IllegalAccessException e) {
						throw new SysException(e);
					} catch (InvocationTargetException e) {
						throw new SysException(e);
					}
				}
			}
		}
	}
	
	/**
	 * 将实体中国的分段字符 转换为 数组存取
	 * 如  obj.name=a/b/c -->obj.names=[a,b,c]
	 * @param 对象实体
	 * @since 
	 * @author chc 2016-11-22 创建方法
	 */
	public static void setSvalue(Object object) {
		if(object == null) {
			return;
		}
		Map<String, Object> values = getObjectFieldAndValues(object);
		Set<Entry<String, Object>> set = values.entrySet();
		for (Entry<String, Object> entry : set) {
			Object xVal = entry.getValue();
			if (BaseUtil.objectNull(xVal)
					|| BaseUtil.stringNull(xVal.toString())) {
				continue;
			}
			
			Method m = null;
			try {
				m = object.getClass().getMethod(
						"set" + StringUtil.toUpperFirst(entry.getKey()) + "s", String.valueOf(xVal).split("/").getClass());
				m.invoke(object, new Object[] { String.valueOf(xVal).split("/")});
			} catch(Exception e) {
				LOGGER.debug("方法不存在，或者调用失败", e);
			}
		}
	}
}
