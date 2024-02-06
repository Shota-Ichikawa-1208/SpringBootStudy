package com.example.SpringBootLogin.domain.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBootLogin.domain.user.model.MUser;
import com.example.SpringBootLogin.domain.user.service.UserService;
import com.example.SpringBootLogin.password_hash.PasswordHash;
import com.example.SpringBootLogin.repository.UserMapper;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired 
	PasswordHash passwordHash;
	
	@Override
	public void signup(MUser user) {
		
		user.setRole("ROLE_GENERAL");
		mapper.insertOne(user);
	}
	
	@Override
	public MUser login(MUser user) {
		MUser finduser = mapper.findOne(user);
		if(finduser != null) {
			boolean result;
			//login時に入力されたpassword
			String input_password = user.getPassword();
			
			result = passwordHash.check_password(finduser, input_password);
			if(result) {
				return finduser;
			}
			
		}
		finduser = null;
		return finduser;
	}
	
}
