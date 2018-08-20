package com.example.demo1;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.github.pagehelper.PageHelper;

@MapperScan(basePackages = "com.example.demo1.domain")
@SpringBootApplication
@Import(com.example.demo1.util.WebMvcConfig.class)
public class Demo1Application{

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}
	//配置mybatis的分页插件pageHelper
	@Bean
	public PageHelper pageHelper(){
	   PageHelper pageHelper = new PageHelper();
	   Properties properties = new Properties();
	   properties.setProperty("offsetAsPageNum","true");
	   properties.setProperty("rowBoundsWithCount","true");
	   properties.setProperty("reasonable","true");
	   properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
	   pageHelper.setProperties(properties);
	   return pageHelper;
	}
}
