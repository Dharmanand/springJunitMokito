package com.example.demo.testcontroller;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.DemoRestController;
import com.example.demo.service.HiService;

@RunWith(SpringJUnit4ClassRunner.class)
public class DemoRestControllerTest {

	private MockMvc mockMvc;

	@Mock
	private HiService hiService;

	@InjectMocks
	private DemoRestController demoRestController;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(demoRestController).build();
	}

	@Test
	public void testHi() throws Exception {
		when(hiService.sayHi()).thenReturn("Hi");
		mockMvc.perform(MockMvcRequestBuilders.get("/hi")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Hi"));
		Mockito.verify(hiService).sayHi();
	}

	@Test
	public void testJson() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/json").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.title", org.hamcrest.Matchers.is("Greetings")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.value", org.hamcrest.Matchers.is("Hello World !!")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.*", org.hamcrest.Matchers.hasSize(2)));

	}

	@Test
	public void testJsonpost() throws Exception {
		String json = "{\n" + " \"title\": \"Greetings\",\n" + "\"value\": \"Hello World !!\"\n" + "}";
		mockMvc.perform(MockMvcRequestBuilders.post("/jsonpost").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.title", org.hamcrest.Matchers.is("Greetings")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.value", org.hamcrest.Matchers.is("Hello World !!")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.*", org.hamcrest.Matchers.hasSize(2)));

	}

}
