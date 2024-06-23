package com.example.SpringBootLogin.controller;

import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.SpringBootLogin.Applicatin.Service.UserApplicationService;
import com.example.SpringBootLogin.domain.order.service.OrderService;
import com.example.SpringBootLogin.domain.user.model.MUser;
import com.example.SpringBootLogin.domain.user.service.UserService;
import com.example.SpringBootLogin.form.SignupForm;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {

	@Autowired
	private HttpSession session;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserApplicationService userApplicationService;

	@GetMapping("/")
	public String getIndex() {
		return "index";
	}

	@GetMapping("/t-shirt")
	public String getT_shirt(Model model, Locale locale) {
		//		Map<String,String> product_colorMap = userApplicationService.getProductColor(locale);
		//		model.addAttribute("product_colorMap", product_colorMap);
		
		//商品の単価取得
		//引数の指定方法はよくない
		String tPrice = String.format("%,d",orderService.getPrice("tカラーTシャツ-black"));
		model.addAttribute("tPrice", tPrice);

		return "product/t-shirt";
	}

	@GetMapping("/jeans")
	public String getJeans(Model model) {
		
		//商品の単価取得
		String jPrice = String.format("%,d",orderService.getPrice("jジーンズ-Black"));
		model.addAttribute("jPrice", jPrice);

		return "product/jeans";
	}

	@PostMapping("/user_page_check")
	public String getUserPage(Model model) {
		String userName = (String) session.getAttribute("userName");

		if (userName != null) {
			model.addAttribute("userName", userName);
			return "user/user_page";
		} else {
			return "redirect:/login_form";
		}
	}

	@GetMapping("/login_form")
	public String getLogin(@ModelAttribute SignupForm form) {
		return "login";
	}

	@GetMapping("/order_cart")
	public String getorder_cart() {
		return "redirect:user/addToCart";
	}

	@PostMapping("/login")
	public String getlogin(Model model, @ModelAttribute SignupForm form) {
		MUser result_user;
		//		formの値をMUser型に変換
		MUser user = modelMapper.map(form, MUser.class);

		result_user = userService.login(user);

		if (result_user != null) {
			String userName = result_user.getUserName();
			session.setAttribute("userName", userName);
			model.addAttribute("userName", userName);
			log.info(userName + "がログイン");
			return "user/user_page";
		} else {
			log.info( "ログイン失敗");
			return "redirect:/login_form";
		}

	}

}
