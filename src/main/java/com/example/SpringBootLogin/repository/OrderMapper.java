package com.example.SpringBootLogin.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.SpringBootLogin.domain.order.model.Order;
@Mapper
public interface OrderMapper {
	//注文の登録
	public int insertOder(Order order);
}
