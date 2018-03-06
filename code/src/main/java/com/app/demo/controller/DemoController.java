package com.app.demo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.demo.model.Demo;
import com.app.demo.service.DemoService;

@Controller
@RequestMapping("/demo")
public class DemoController {
	
	@Resource
	private DemoService demoService;
	
	@RequestMapping("/test")
	@ResponseBody
	public List<Demo> test() {
		return demoService.findDemo();
		
	}

}
