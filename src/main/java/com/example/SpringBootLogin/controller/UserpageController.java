package com.example.SpringBootLogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("user")
@Slf4j
public class UserpageController {
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/logout")
	public String getLogOut() {
		String userName = (String)session.getAttribute("userName");
		session.invalidate();
		log.info(userName + "がログアウトしました");
		return "redirect:/";
	}
	
	@GetMapping("/cart")
	public String getOrderCart() {
		return "/user/order_cart";
	}

}
