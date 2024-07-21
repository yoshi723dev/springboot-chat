package com.springboot.chat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.chat.entity.TChatGroup;

@Mapper
public interface TChatGroupMapper {

	TChatGroup findOne(@Param("user_id") int user_id);

	int insert(TChatGroup tChatGroup);

}
