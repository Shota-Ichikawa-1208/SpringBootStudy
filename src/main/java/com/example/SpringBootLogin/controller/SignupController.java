package com.example.SpringBootLogin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.SpringBootLogin.Applicatin.Service.UserApplicationService;

@Controller
@RequestMapping("/user")
public class SignupController {
	
	@Autowired
	private UserApplicationService userApplicationService;
	
	@GetMapping("/signup")
	public String getSinup(Model model) {
		Map<String, Integer> genderMap = userApplicationService.getGenderMap();
		
//		sessionに追加のイメージ？
		model.addAttribute("genderMap", genderMap);
		return "/user/signup";
		
		
	//後でユーザーページに飛ばす処理記述リダイレクトする
	}
}
