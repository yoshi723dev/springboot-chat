package com.springboot.chat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.chat.entity.TFrend;

@Mapper
public interface TFrendMapper {

	TFrend findOne(@Param("user_id") int user_id);

	int insert(TFrend tFrend);

}
