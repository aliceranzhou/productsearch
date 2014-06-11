package com.productsearch.config;

import javax.servlet.Filter;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//spring configuration used by the web application to set up servlet
@Order(2)
public class WebAppInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	// {!begin addToRootContext}
	@Override
	protected Class<?>[] getRootConfigClasses() {	//gives configuration class
		return new Class<?>[] { CoreConfig.class };
	}

	// {!end addToRootContext}

	@Override
	protected Class<?>[] getServletConfigClasses() {	//gives configuration class needed for servlet
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {	//servlet mapping is prefaced with "/"
		return new String[] { "/" };
	}

	@Override	
	protected Filter[] getServletFilters() {	//encodes characters with "UTF-8"
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		return new Filter[] { characterEncodingFilter };
	}

}
