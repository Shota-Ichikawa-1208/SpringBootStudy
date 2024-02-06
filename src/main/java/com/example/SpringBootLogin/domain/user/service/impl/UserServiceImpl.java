package com.example.SpringBootLogin.domain.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBootLogin.domain.user.model.MUser;
import com.example.SpringBootLogin.domain.user.service.UserService;
import com.example.SpringBootLogin.repository.UserMapper;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper mapper;
	
	@Override
	public void signup(MUser user) {
		
		user.setRole("ROLE_GENERAL");
		mapper.insertOne(user);
	}
}
