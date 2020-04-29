package com.genpact.library.controller.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import com.genpact.library.controller.LibraryController;

public class LibraryControllerTest {

	public static final String INDEX = "index";
	public static final String ROOT_URL = "/";

	private LibraryController controller;
	private MockMvc mockMvc;

	@Before
	public void beforeTest() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(getController()).build();
	}

	@Test
	public void testShowApplication() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(ROOT_URL))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		ModelAndView result = mvcResult.getModelAndView();
		Assert.assertEquals("View returned", INDEX, result.getViewName());
	}

	public LibraryController getController() {
		if (null == controller) {
			controller = new LibraryController();
		}
		return controller;
	}
}
