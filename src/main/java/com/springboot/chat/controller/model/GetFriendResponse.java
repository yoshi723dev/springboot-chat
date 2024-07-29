package com.springboot.chat.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GetFriendResponse extends AbstractResponse {
    
    @JsonProperty("friends")
    private Friends[] friends;

    @Data
    public class Friends {
        @JsonProperty("chat_group_id") 
        private int chatGroupId;
        
        @JsonProperty("user_id")
        private int userId;
        
        @JsonProperty("user_nm")
        private String userNm;
    }
}