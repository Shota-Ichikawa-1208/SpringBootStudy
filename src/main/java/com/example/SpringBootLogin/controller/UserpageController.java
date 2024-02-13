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
import com.example.SpringBootLogin.domain.order.service.OrderService;
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
	
	@Autowired
	private OrderService orderService;
	
	
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
		int total_amount = 0;
		int orders = 0;
		if(user_orderList != null) {
			total_amount = orderService.calculationTotal(user_orderList);
			orders = user_orderList.size();
		}
		model.addAttribute("user_orderList", user_orderList);
		model.addAttribute("total_amount",total_amount);
		model.addAttribute("orders", orders);
		return "/user/order_cart";
	}
	
	@PostMapping("/addToCart")
	public String getaddToCart(Model model,@ModelAttribute OrderForm form, HttpSession session) {
		Order user_order = modelMapper.map(form, Order.class);
		
		//暫定処理小計設定
		user_order.setSubtotal(user_order.getQuantity() * 1000);
		
		//注文合計金額
		int total_amount;
		//注文件数
		int orders;
		
		if(session.getAttribute("user_orderList") == null) {
			List<Order> user_orderList = userApplicationService.getOrderList();
			user_orderList.add(user_order);
			total_amount = orderService.calculationTotal(user_orderList);
			orders = user_orderList.size();
			
			session.setAttribute("user_orderList", user_orderList);
			model.addAttribute("user_orderList", user_orderList);
			model.addAttribute("total_amount",total_amount);
			model.addAttribute("orders", orders);
		}else {
			List<Order> user_orderList = (List<Order>) session.getAttribute("user_orderList");
			user_orderList.add(user_order);
			total_amount = orderService.calculationTotal(user_orderList);
			orders = user_orderList.size();
			
			session.setAttribute("user_orderList", user_orderList);
			model.addAttribute("user_orderList", user_orderList);
			model.addAttribute("total_amount",total_amount);
			model.addAttribute("orders", orders);
		}
		
		return "/user/order_cart";
	}
	
	@PostMapping("/delete_order")
	public String deleteOder(Model model, HttpSession session, @ModelAttribute("cart_index") int delete_index){
		List<Order> user_orderList = (List<Order>) session.getAttribute("user_orderList");
		user_orderList.remove(delete_index);
	
		return "redirect:addToCart";
	}
	
	@PostMapping("/purchase")
	public String Purchase(Model model, HttpSession session) {
		String userName = (String)session.getAttribute("userName");
		List<Order> user_orderList = (List<Order>) session.getAttribute("user_orderList");
		//ログインしていない場合loginページに飛ばす
		if (userName == null) {
			return "redirect:/login_form";
		}
		
		//注文が一件もない場合order_cartに戻す
		if (user_orderList.size() == 0) {
			return "redirect:addToCart";
		}
		
		//Orderに注文者の名前を追加する
		for(Order order : user_orderList) {
			order.setUserName(userName);
		}
		orderService.order(user_orderList);
		user_orderList.clear();
		
		return "redirect:addToCart";
	}
}
