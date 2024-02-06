package com.example.SpringBootLogin.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.SpringBootLogin.domain.user.model.MUser;
import com.example.SpringBootLogin.domain.user.service.UserService;
import com.example.SpringBootLogin.form.SignupForm;

@Controller
public class MainController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserService userService;
	
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
	public String getLogin(@ModelAttribute SignupForm form) {
		return "login";
	}
	
	@PostMapping("/login")
	public String getUsrPage(Model model, @ModelAttribute SignupForm form) {
		MUser result_user;
//		formの値をMUser型に変換
		MUser user = modelMapper.map(form, MUser.class);
		
		
		result_user = userService.login(user);
		
		if(result_user != null) {
			String userName = result_user.getUserName();
			model.addAttribute("userName", userName);
			return "/user/user_page";
		}else {
	        return "redirect:/login_form";
		}
		
	}
	
}
