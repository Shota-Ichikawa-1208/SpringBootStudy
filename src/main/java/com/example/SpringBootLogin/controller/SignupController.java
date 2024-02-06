package com.example.SpringBootLogin.controller;

import java.util.Locale;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.SpringBootLogin.Applicatin.Service.UserApplicationService;
import com.example.SpringBootLogin.domain.user.model.MUser;
import com.example.SpringBootLogin.domain.user.service.UserService;
import com.example.SpringBootLogin.form.GroupOrder;
import com.example.SpringBootLogin.form.SignupForm;
import com.example.SpringBootLogin.password_hash.PasswordHash;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class SignupController {
	
	@Autowired
	private UserApplicationService userApplicationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired 
	PasswordHash passwordHash;
	
	@GetMapping("/signup")
	public String getSignup(Model model, Locale locale,
			@ModelAttribute SignupForm form) {
		Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
		
//		sessionに追加のイメージ？
		model.addAttribute("genderMap", genderMap);
		return "/user/signup";
	}
		
	@PostMapping("/signup")
	public String postSignup(Model model,Locale locale, @ModelAttribute@Validated(GroupOrder.class)SignupForm form,
			BindingResult bindingResult) {
		
//		入力チェックの結果
		if(bindingResult.hasErrors()) {
			//ユーザー登録画面に戻す
			return getSignup(model,locale,form);
		}
		
		//入力されたPWをハッシュ化
		String password = form.getPassword();
		form.setPassword(passwordHash.make_hash_password(password));
		
		//DBに登録
		MUser user = modelMapper.map(form, MUser.class);
		
		userService.signup(user);
		
		String userName = form.getUserName();
		model.addAttribute("userName", userName);
		log.info(form.toString());
		
		
		return "/user/user_page";
		
	}
	
}
