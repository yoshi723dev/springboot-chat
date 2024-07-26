package com.springboot.chat.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GetFriendResponse {
	
	@JsonProperty("friends")
	private Friends[] friends;

	@Data
	public class Friends {
		@JsonProperty("user_id")
		private int userId;
		
		@JsonProperty("user_nm")
		private String userNm;
	}
}