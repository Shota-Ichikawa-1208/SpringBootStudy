package com.example.SpringBootLogin.password_hash;

import com.example.SpringBootLogin.domain.user.model.MUser;

public interface PasswordHash {
	public String make_hash_password(String password);
}
