package com.example.SpringBootLogin.domain.order.service;

import java.util.List;

import com.example.SpringBootLogin.domain.order.model.Order;

public interface OrderService {
	public int calculationTotal(List<Order> user_orderList);
	
	//注文１件の登録
	public void order(List<Order> user_orderList); 
}
