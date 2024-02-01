package com.example.SpringBootLogin.controller;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.SpringBootLogin.Applicatin.Service.UserApplicationService;
import com.example.SpringBootLogin.form.SignupForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class SignupController {
	
	@Autowired
	private UserApplicationService userApplicationService;
	
	@GetMapping("/signup")
	public String getSinup(Model model, Locale locale,
			@ModelAttribute SignupForm form) {
		Map<String, Integer> genderMap = userApplicationService.getGenderMap();
		
//		sessionに追加のイメージ？
		model.addAttribute("genderMap", genderMap);
		return "/user/signup";
	}
		
	//後でユーザーページに飛ばす処理記述リダイレクトする
	@PostMapping("/signup")
	public String postSignup(Model model, @ModelAttribute SignupForm form) {
		String userName = form.getUserName();
		model.addAttribute("userName", userName);
		log.info(form.toString());
		return "/user/user_page";
		
	}
	
}
