package com.springboot.chat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.chat.entity.TChatLog;

@Mapper
public interface TChatGroupMapper {

    int findChatGroup(@Param("user_id") int user_id, @Param("friend_user_id") int friend_user_id);

	int insert(TChatLog tChatLog);

}
