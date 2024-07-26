package com.springboot.chat.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegistChatLogRequest {
	
	@JsonProperty("chat_group_id") 
	private int chatGroupId;

	@JsonProperty("message") 
	private String message;
}