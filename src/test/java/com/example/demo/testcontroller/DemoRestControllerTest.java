package com.example.demo.testcontroller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.DemoRestController;

@RunWith(SpringJUnit4ClassRunner.class)
public class DemoRestControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private DemoRestController demoRestController;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(demoRestController).build();
	}

	@Test
	public void testHi() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/hi")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Hi"));
	}

	@Test
	public void testJson() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/json").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.title", org.hamcrest.Matchers.is("Greetings")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.value", org.hamcrest.Matchers.is("Hello World !!")));

	}

}
