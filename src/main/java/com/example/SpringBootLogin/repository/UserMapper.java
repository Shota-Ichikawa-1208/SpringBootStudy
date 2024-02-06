package com.example.SpringBootLogin.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.SpringBootLogin.domain.user.model.MUser;

@Mapper
public interface UserMapper {
	
	/* ユーザー登録*/
	public int insertOne(MUser user);
	
	/*ユーザー検索*/
	public MUser findOne(MUser user);
	
	
}
