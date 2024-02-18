package com.example.SpringBootLogin.product;

import lombok.Data;

@Data
public class OrderForm {
	private String product_color;
	private String size;
	private Integer quantity;
	private String price;
}
