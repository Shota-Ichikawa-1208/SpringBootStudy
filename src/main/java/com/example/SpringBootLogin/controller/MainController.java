package com.example.SpringBootLogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
	
	@GetMapping("/")
	public String getIndex() {
		return "index";
	}
	
	@GetMapping("/t-shirt")
	public String getT_shirt() {
		return "/product/t-shirt";
	}
	
	@GetMapping("/jeans")
	public String getJeans() {
		return "/product/jeans";
	}
	
	@GetMapping("/login_form")
	public String getLogin() {
		return "login";
	}
	
}
