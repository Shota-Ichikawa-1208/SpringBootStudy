package com.example.SpringBootLogin.domain.order.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Order implements Serializable{
	private String userName;
	private String product_color;
	private String size;
	private Integer quantity;
	private String price;
	private Integer subtotal;
}
