package com.productsearch.config;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { CoreConfig.class, WebConfig.class })
public class WebIntegrationTest {

	private MockMvc mockMvc;

	private static final String NAME = "ball";
	private static final String POSTAL_CODE = "n2v2k7";

	@Autowired
	WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void displaysGet() throws Exception {
		mockMvc.perform(get("/")).andDo(print());
	}

	@Test
	public void submitsCorrectly() throws Exception {
		mockMvc.perform(
				post("/").param("keyWords", NAME).param("postalCode",
						POSTAL_CODE)).andDo(print());
	}

}
