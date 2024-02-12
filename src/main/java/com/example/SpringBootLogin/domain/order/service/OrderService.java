package com.example.SpringBootLogin.domain.order.service;

import java.util.List;

import com.example.SpringBootLogin.domain.order.model.Order;

public interface OrderService {
	public int calculationTotal(List<Order> user_orderList);
}
