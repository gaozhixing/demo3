package com.example.demo1.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(ConstantUtil.imgPath.STATIC_URL).addResourceLocations(ConstantUtil.imgPath.STATIC_IMG);
        super.addResourceHandlers(registry);
    }

}