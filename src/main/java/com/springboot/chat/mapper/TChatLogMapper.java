package com.springboot.chat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.chat.entity.TChatLog;

@Mapper
public interface TChatLogMapper {

    TChatLog findOne(@Param("chat_group_id") int chat_group_id);

	int insert(TChatLog tChatLog);

}
