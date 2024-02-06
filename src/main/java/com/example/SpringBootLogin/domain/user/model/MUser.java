package com.example.SpringBootLogin.domain.user.model;

import java.util.Date;

import lombok.Data;

@Data
public class MUser {
	private String userName;
	private String userId;
	private String password;
	private Date birthday;
	private Integer age;
	private Integer gender;
	private String role;
}
