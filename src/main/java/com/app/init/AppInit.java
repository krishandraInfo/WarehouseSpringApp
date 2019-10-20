package com.app.init;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.app.config.AppConfig;

//web.xml 
public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer{ 

	@Override  
	protected Class<?>[] getRootConfigClasses() {   
		return new Class[] {AppConfig.class};  
	} 

	@Override 
	protected Class<?>[] getServletConfigClasses() {  
		return null;  
		} 

	@Override  
	protected String[] getServletMappings() {  
		return new String[] {"/"};  
		} 
} 