package com.app;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configurable
@SpringBootApplication
//@ImportResource({"classpath:application.xml"})
@EnableTransactionManagement
@EnableAutoConfiguration
public class App extends SpringBootServletInitializer {
	
	/**
	 * 兼容tomcat启动
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(App.class);
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}
	
}
