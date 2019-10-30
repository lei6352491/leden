package com.zhouyi.business.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zhouyi.business.core.common.Config;

@Component
public class WebConfigurer extends WebMvcConfigurerAdapter{
	@Autowired
	private Config config;
	
	@Override  
    public void addResourceHandlers(ResourceHandlerRegistry registry) {  
        registry.addResourceHandler("/files/**").addResourceLocations("file:///"+config.getUploadPath()+"/");  
    } 
}
