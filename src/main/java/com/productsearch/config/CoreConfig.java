package com.productsearch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.productsearch.core.services.item.ItemService;
import com.productsearch.core.services.item.ItemServiceHandler;
import com.productsearch.core.services.search.AmazonDriver;
import com.productsearch.core.services.search.EbayDriver;
import com.productsearch.core.services.search.SearchService;
import com.productsearch.core.services.search.SearchServiceHandler;
import com.productsearch.web.domain.Search;

//Spring configuration for the various beans used

@Configuration
public class CoreConfig {
	@Bean
	public SearchService searchService() {
		return new SearchServiceHandler();
	}

	@Bean
	public ItemService itemService() {
		return new ItemServiceHandler();
	}

	@Bean
	public Search search() {
		return new Search();
	}

	@Bean
	public AmazonDriver amazonDriver() {
		return new AmazonDriver();
	}

	@Bean
	public EbayDriver ebayDriver() {
		return new EbayDriver();
	}
}
