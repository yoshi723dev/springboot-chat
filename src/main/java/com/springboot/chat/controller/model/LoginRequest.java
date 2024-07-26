package com.springboot.chat.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginRequest {
	//@NotEmpty(message = "ユーザIDは必須入力です。")
    //@Size(max = 20, message = "ユーザIDは{max}桁以内で入力して下さい。")
	@JsonProperty("user_id") 
	private int userId;

	//@NotEmpty(message = "パスワードは必須入力です。")
    //@Size(max = 10, message = "パスワードは{max}桁以内で入力して下さい。")
	@JsonProperty("password") 
	private String password;
}