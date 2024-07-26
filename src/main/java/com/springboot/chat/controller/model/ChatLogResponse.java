package com.springboot.chat.controller.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ChatLogResponse {
	
	@JsonProperty("chat_group_id")
	private int chatGroupId;
	
	@JsonProperty("chatLog")
	private ChatLog[] chatLog;

	@Data
	public class ChatLog {
		@JsonProperty("user_id")
		private int user_id;
		
		@JsonProperty("chat_date")
		private Date chat_date;
		
		@JsonProperty("comment")
		private String comment;
	}
}