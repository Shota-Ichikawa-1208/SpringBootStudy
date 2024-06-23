package com.example.SpringBootLogin.domain.order.service;

import java.util.List;

import org.springframework.ui.Model;

import com.example.SpringBootLogin.domain.order.model.Order;

public interface OrderService {
	public int calculationTotal(List<Order> user_orderList);
	
	//注文１件の登録
	public void order(List<Order> user_orderList, Model model); 
	
	//商品の単価取得
	public int getPrice(String productType);
}
