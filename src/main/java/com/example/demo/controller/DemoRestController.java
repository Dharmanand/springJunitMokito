package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.HiService;

@RestController
public class DemoRestController {

	@Autowired
	private HiService hiService;

	@GetMapping(value = "/hi")
	public String hi() {
		return hiService.sayHi();
	}

	@GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public Hello json() {
		return new Hello("Greetings", "Hello World !!");
	}

	@PostMapping(value = "/jsonpost", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Hello jsonpost(@RequestBody Hello hello) {
		return hello;
	}

	private static class Hello {
		private String title;
		private String value;

		public Hello() {

		}

		public Hello(String title, String value) {
			super();
			this.title = title;
			this.value = value;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

}
