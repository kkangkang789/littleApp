package com.app.demo.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.app.base.GenericDao;
import com.app.demo.model.Demo;

@Repository
public class DemoDao extends GenericDao<Demo> {

	
	@SuppressWarnings("unchecked")
	public List<Demo> queryAll() {
		String sql = "select o from Demo o";
		Query query = this.entityManager.createQuery(sql);
		return query.getResultList();
	}
}
