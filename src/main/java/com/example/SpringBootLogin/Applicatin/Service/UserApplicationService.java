package com.example.SpringBootLogin.Applicatin.Service;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.example.SpringBootLogin.domain.order.model.Order;


@Service
public class UserApplicationService {
	
	
	@Autowired
	private MessageSource messageSource;
	
	public Map<String, Integer> getGenderMap(Locale locale){
		Map<String, Integer> genderMap = new  LinkedHashMap<>();
		String male = messageSource.getMessage("male",null,locale);
		String female = messageSource.getMessage("female",null,locale);
		genderMap.put(male, 1);
		genderMap.put(female, 2);
		return genderMap;
	}
	
	
	public List<Order> getOrderList(){
		List<Order> user_orderList = new ArrayList<>();
		return user_orderList;
	}
}
