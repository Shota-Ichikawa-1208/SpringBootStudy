package com.example.SpringBootLogin.domain.order.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SpringBootLogin.domain.order.model.Order;
import com.example.SpringBootLogin.domain.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	private int TShirtPrice = 1000;
	private int JeansPrice = 1000;
	
	
	public int calculationTotal(List<Order> user_orderList) {
		int total_amount = 0;
		for(Order order : user_orderList) {
			total_amount += order.getQuantity() * TShirtPrice;
		}
		return total_amount;
	}
}
