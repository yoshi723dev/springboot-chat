package com.springboot.chat.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginRequest {
    @NotNull(message = "ユーザIDは必須入力です。")
    @JsonProperty("user_id") 
    private int userId;

    @NotEmpty(message = "パスワードは必須入力です。")
    @JsonProperty("password") 
    private String password;
}