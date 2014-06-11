package com.productsearch.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.productsearch.core.services.search.SearchService;
import com.productsearch.web.domain.Search;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CoreConfig.class })
public class CoreIntegrationTest {

	@Autowired
	SearchService searchservice;

	@Test
	public void searchCreated() {
		Search search = new Search();
		search.setKeyWords("ball");
		searchservice.setSearch(search);
		assertEquals("ball", searchservice.getSearch().getKeyWords());
	}
}
