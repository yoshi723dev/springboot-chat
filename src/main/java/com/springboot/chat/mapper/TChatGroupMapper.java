package com.springboot.chat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.chat.entity.TChatGroup;

@Mapper
public interface TChatGroupMapper {

	TChatGroup find(@Param("chat_group_id") int chat_group_id);
	
	TChatGroup findUserId(@Param("chat_group_id") int chat_group_id, @Param("user_id") int user_id);

    int findMax();
    
	int insert(@Param("chat_group_id") int chat_group_id, @Param("user_id") int user_id);

}
