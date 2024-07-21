package com.springboot.chat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.chat.entity.MUser;

@Mapper
public interface MUserMapper {

    MUser findOne(@Param("user_id") int user_id);

	int insert(MUser mUser);

}
