package com.springboot.chat.controller.model;

import lombok.Data;

@Data
public class LoginRequest {
	//@NotEmpty(message = "ユーザIDは必須入力です。")
    //@Size(max = 20, message = "ユーザIDは{max}桁以内で入力して下さい。")
	private int user_id;

	//@NotEmpty(message = "パスワードは必須入力です。")
    //@Size(max = 10, message = "パスワードは{max}桁以内で入力して下さい。")
	private String password;
}