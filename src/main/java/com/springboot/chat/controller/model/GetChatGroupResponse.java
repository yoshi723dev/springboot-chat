package com.springboot.chat.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GetChatGroupResponse {

	@JsonProperty("list_chat_group") 
	private ChatGroup[] listChatGroup;
	
	@Data
	public class ChatGroup {
		@JsonProperty("chat_group_id") 
		private int chatGroupId;
		
		@JsonProperty("chat_name") 
		private String chatName;
		
		@JsonProperty("friend_user_ids") 
		private int[] friend_user_ids;
	}
}