package com.example.SpringBootLogin.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.SpringBootLogin.Applicatin.Service.UserApplicationService;
import com.example.SpringBootLogin.domain.order.model.Order;
import com.example.SpringBootLogin.product.OrderForm;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
@Controller
@RequestMapping("user")
@Slf4j
public class UserpageController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserApplicationService userApplicationService;
	
	
	@GetMapping("/logout")
	public String getLogOut() {
		String userName = (String)session.getAttribute("userName");
		session.invalidate();
		log.info(userName + "がログアウトしました");
		return "redirect:/";
	}
	
	@GetMapping("/addToCart")
	public String getOrder_cart(Model model, HttpSession session) {
		List<Order> user_orderList = (List<Order>) session.getAttribute("user_orderList");
		model.addAttribute("user_orderList", user_orderList);
		return "/user/order_cart";
	}
	
	@PostMapping("/addToCart")
	public String getaddToCart(Model model,@ModelAttribute OrderForm form, HttpSession session) {
		Order user_order = modelMapper.map(form, Order.class);
		
		if(session.getAttribute("user_orderList") == null) {
			List<Order> user_orderList = userApplicationService.getOrderList();
			user_orderList.add(user_order);
			session.setAttribute("user_orderList", user_orderList);
			model.addAttribute("user_orderList", user_orderList);
		}else {
			List<Order> user_orderList = (List<Order>) session.getAttribute("user_orderList");
			user_orderList.add(user_order);
			session.setAttribute("user_orderList", user_orderList);
			model.addAttribute("user_orderList", user_orderList);
		}
		
		return "/user/order_cart";
	}

}
