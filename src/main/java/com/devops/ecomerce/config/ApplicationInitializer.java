package com.devops.ecomerce.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{ViewResolverConfiguration.class,MvcConfig.class,SecurityConfiguration.class};
	}
	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return null;
	}
	@Override
	protected String[] getServletMappings() {
		return  new String[]{"/"};
	}

}
