package com.springboot.chat.controller.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ChatLogResponse extends AbstractResponse {
	
	@JsonProperty("chat_group_id")
	private int chatGroupId;
	
	@JsonProperty("chatLog")
	private ChatLog[] chatLog;

	@Data
	public class ChatLog {
		@JsonProperty("my_message")
		private int my_message;
		
		@JsonProperty("chat_date")
		private Date chat_date;
		
		@JsonProperty("comment")
		private String comment;
	}
}