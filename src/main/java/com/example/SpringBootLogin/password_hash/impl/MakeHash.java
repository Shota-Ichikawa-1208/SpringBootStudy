package com.example.SpringBootLogin.password_hash.impl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SpringBootLogin.domain.user.model.MUser;
import com.example.SpringBootLogin.password_hash.PasswordHash;
@Service
public class MakeHash implements PasswordHash{

	public String make_hash_password(String password) {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		
		String hashed_password = bcpe.encode(password);
		return hashed_password;
		
	}
	
	public boolean check_password(MUser User, String input_password) {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		
//		DBに登録されているpassword
		String db_password = User.getPassword();
		
		return bcpe.matches(input_password, db_password);
	}
}
