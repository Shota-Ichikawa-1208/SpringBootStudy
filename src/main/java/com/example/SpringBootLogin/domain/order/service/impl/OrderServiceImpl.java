package com.example.SpringBootLogin.domain.order.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.example.SpringBootLogin.domain.order.model.Order;
import com.example.SpringBootLogin.domain.order.service.OrderService;
import com.example.SpringBootLogin.repository.OrderMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderMapper ordermapper;

	private int TShirtPrice = 1000;
	private int JeansPrice = 1000;

	public int calculationTotal(List<Order> user_orderList) {
		int total_amount = 0;
		for (Order order : user_orderList) {
			total_amount += order.getQuantity() * TShirtPrice;
		}
		return total_amount;
	}

	@Transactional
	public List<Order> order(List<Order> user_orderList, Model model) {
		int ordered_count = 0;
		//在庫が足りず注文できなかった商品を格納
		List<Order> not_orderedList =new ArrayList<>();
		for (Order one_order : user_orderList) {
			int result = ordermapper.insertOder(one_order);
			if (result == 0) {
				log.info("在庫不足のため注文できませんでした");
				not_orderedList.add(one_order);
			}else {
				ordermapper.reduceInventory(one_order);
				//注文が確定したときのみcount++;
				ordered_count++;
			}

		}
		model.addAttribute("ordered_count",ordered_count);
		if(not_orderedList.size() != 0) {
			model.addAttribute("not_orderedList", not_orderedList);
		}
		return not_orderedList;
	}
}
