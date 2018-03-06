/**  
 * @Copyright ginkgo
 */

package com.app.base;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.app.base.util.BaseUtil;
import com.app.base.util.ClassUtil;

/**
 * 通用dao,包含常规操作
 *
 * @author iccboy
 * @date 2015-5-21
 */
@Repository
@Transactional
public class GenericDao<T> {
	@PersistenceContext
	protected EntityManager entityManager;
	
	/**
	 * 对应实体类
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> entityClass =
			(Class<T>) ClassUtil.getEntityClass(this.getClass());
	
	/**
	 * 实体名字
	 */
	protected String entityName = getEntityName();


	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * 新增对象
	 */
	public void save(final Object entity) {
		entityManager.persist(entity);
	}

	/**
	 * 更新对象
	 */
	public void update(T entity) {
		entityManager.merge(entity);
	}

	/**
	 * 删除对象
	 */
	public void delete(final Object entity) {
		entityManager.remove(entity);
	}

	/**
	 * 以id删除对象
	 */
	public void deleteById(Object id, Class<T> entityClass) {
		entityManager.remove(findById(id, entityClass));
	}

	/**
	 * 以id查找对象
	 */
	public T findById(Object id, Class<T> entityClass) {
		return entityManager.find(entityClass, id);
	}

	/**
	 * 以hql查找一个对象
	 */
	public T findOneByHql(String hql, Class<T> entityClass) {
		Query query = entityManager.createQuery(hql);
		T obj = null;
		try {
			obj = entityClass.cast(query.getSingleResult());
		} catch (NoResultException e) {
		}

		return obj;
	}

	/**
	 * 以hql查找全部对象
	 */
	public List<T> findAllByHql(String hql, Class<T> entityClass) {
		Query query = entityManager.createQuery(hql);
		List<T> objList = new LinkedList<T>();
		try {
			List<?> tmpList = query.getResultList();
			if (tmpList != null) {
				Iterator<?> iter = tmpList.iterator();
				while (iter.hasNext()) {
					T obj = entityClass.cast(iter.next());
					objList.add(obj);
				}
			}
		} catch (NoResultException e) {
		}

		return objList;
	}
	
	/**
	 * 条件查询
	 * @param queryStruct 查询结构体
	 * @return 查询结果
	 * @since 1.0
	 * @author kyang 2017年5月24日 创建方法
	 *//*
	public QueryResult<T> query(QueryStruct<T> queryStruct) {
		
		 参数准备 
		 排序 后期再做 
		//Map<String, String> orderBy = queryStruct.getOrderby();
		 当前页数 
		Integer pageNo = queryStruct.getPageNo();
		 每页条数 
		Integer pageSize = queryStruct.getPageSize();
		 查询参数 
		List<Object> params =  queryStruct.getParams();
		 查询语句  
		String whereSql = queryStruct.getWhereSql();
		
		String resultSql = "select o from " + entityName + " o " + whereSql + "order by o.id DESC";
		String countSql = "select count(*) from " + entityName + " o " + whereSql + "order by o.id DESC";
		
		Query query = this.entityManager.createQuery(resultSql);
		setParameters(query, params);
		if(queryStruct.isPage()) {
			int start = (pageNo - 1) * pageSize;
			query.setFirstResult(start);
			query.setMaxResults(pageSize);
		}
		
		@SuppressWarnings("unchecked")
		List<T> results = query.getResultList();
		Long count = 0L;
		int totalPageNum = 0;
		if (queryStruct.isPage()) {
			query = this.entityManager.createQuery(countSql);
			setParameters(query, params);
			count = (Long) query.getSingleResult();
			totalPageNum = (int) (count / pageSize);
			if (count % pageSize != 0) {
				totalPageNum++;
			}
		} else {
			count = Long.valueOf(results.size());
			totalPageNum = 1;
		}
		*//**结果返回**//*
		QueryResult<T> queryResults = new QueryResult<T>();
		queryResults.setCurrentPageNum(pageNo);
		queryResults.setPerPageNum(pageSize);
		queryResults.setResultList(results);
		queryResults.setTotalPageNum(totalPageNum);
		queryResults.setTotalRecordNum(count);
		
		return queryResults;
	}*/
	
	/**
	 * 设置参数
	 * @param query query查询体
	 * @param params 参数
	 * @since 1.0
	 * @author chc 2014-7-16 创建方法
	 */
	public void setParameters(Query query, List<Object> params) {
		if (null != query && BaseUtil.listNotNull(params)) {
			for (int i = 1; i <= params.size(); i++) {
				query.setParameter(i, params.get(i - 1));
			}
		}
	}
	
	/**
	 * 获得实体类名
	 * @return 实体名
	 * @since 1.0
	 * @author chc 2014-7-13 创建方法
	 */
	private String getEntityName() {
		String enName = entityClass.getSimpleName();
		Entity entity = entityClass.getAnnotation(Entity.class);
		String name = null;
		if (null != entity) {
			name = entity.name();
		}
		return null == name || "".equals(name.trim()) ? enName : name.trim();
	}
	
}
