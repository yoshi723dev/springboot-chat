package com.springboot.chat.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegistFriendRequest {
	@JsonProperty("friend_ids") 
	private int[] friendIds;
}