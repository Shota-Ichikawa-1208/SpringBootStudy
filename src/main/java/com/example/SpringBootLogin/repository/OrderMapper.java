package com.example.SpringBootLogin.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.SpringBootLogin.domain.order.model.Order;
@Mapper
public interface OrderMapper {
	//注文の登録
	public int insertOder(Order order);
	//注文時の在庫管理
	public int reduceInventory(Order order);
	//商品の単価取得
	public int getPrice(String productType);
}
