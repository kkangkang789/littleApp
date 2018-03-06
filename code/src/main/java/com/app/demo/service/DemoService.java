package com.app.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.demo.dao.DemoDao;
import com.app.demo.model.Demo;

@Service
public class DemoService {

	
	@Resource
	private DemoDao demoDao;
	
	public List<Demo> findDemo() {
		List<Demo> result = this.demoDao.queryAll();
		for(Demo demo : result) {
			System.out.println(demo.getId() + " " + demo.getName() + " " + demo.getSex() + " " + demo.getAge());
		}
		return result;
	}
}
