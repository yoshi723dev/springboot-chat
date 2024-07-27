package com.springboot.chat.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ErrorResponse {
	@JsonProperty("status")
	private int status;

	@JsonProperty("message")
	private String 	message;
	
	public ErrorResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}
}
