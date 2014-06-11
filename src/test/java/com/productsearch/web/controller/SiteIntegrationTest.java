package com.productsearch.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.productsearch.config.CoreConfig;
import com.productsearch.core.services.search.SearchService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ContextConfiguration(classes = { CoreConfig.class })
public class SiteIntegrationTest {

	private static final String FORWARDED_URL = "/WEB-INF/views/searchForm.html";
	private static final String VIEW = "/searchForm";
	private static final String NAME = "ball";
	private static final String POSTAL_CODE = "n2v2k7";

	MockMvc mockMvc;

	@InjectMocks
	SearchFormController controller;

    @Mock
    SearchService searchService;
    
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		mockMvc = standaloneSetup(controller).setViewResolvers(viewResolver())
				.build();
	}

	private InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views");
		viewResolver.setSuffix(".html");
		return viewResolver;
	}

	@Test
	public void rootUrlforwardsCorrectly() throws Exception {
		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(view().name(VIEW))
				.andExpect(forwardedUrl(FORWARDED_URL));
	}

	@Test
	public void submitsCorrectly() throws Exception {
		mockMvc.perform(
				post("/").param("keyWords", NAME).param("postalCode",
						POSTAL_CODE)).andDo(print());
	}
}
