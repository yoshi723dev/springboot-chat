package com.springboot.chat.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegistChatLogRequest {
    @NotNull(message = "チャットグループIDがありません。")
    @JsonProperty("chat_group_id") 
    private int chatGroupId;
    
    @NotEmpty(message = "メッセージがありません。")
    @JsonProperty("message") 
    private String message;
}