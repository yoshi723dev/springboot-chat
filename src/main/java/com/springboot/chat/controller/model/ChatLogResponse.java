package com.springboot.chat.controller.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ChatLogResponse {
	
	@JsonProperty("chats")
	private Chats[] chats;

	@Data
	public class Chats {
		@JsonProperty("user_id")
		private int user_id;
		
		@JsonProperty("chat_date")
		private Date chat_date;
		
		@JsonProperty("comment")
		private String comment;
	}
}