package com.springboot.chat.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GetChatLogRequest {

	@JsonProperty("chat_group_id") 
	private int chatGroupId;

	@JsonProperty("frend_user_ids") 
	private int[] frendUserIds;
}