package com.springboot.chat.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GetChatLogResponse extends AbstractResponse {
	
	@JsonProperty("chat_group_id")
	private int chatGroupId;
	
	@JsonProperty("chatLog")
	private ChatLog[] chatLog;

	@Data
	public class ChatLog {
		@JsonProperty("my_message")
		private int my_message;
		
		@JsonProperty("user_nm")
		private String user_nm;
		
		@JsonProperty("chat_date")
		private String chat_date;
		
		@JsonProperty("comment")
		private String comment;
	}
}