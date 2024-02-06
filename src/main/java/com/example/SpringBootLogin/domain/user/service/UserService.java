package com.example.SpringBootLogin.domain.user.service;

import com.example.SpringBootLogin.domain.user.model.MUser;

public interface UserService {
	/*ユーザー登録*/
	public void signup(MUser user);
	
	/*loginユーザー検索*/
	public MUser login(MUser user);
}
