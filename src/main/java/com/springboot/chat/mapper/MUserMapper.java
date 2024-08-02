package com.springboot.chat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.chat.entity.MUser;

@Mapper
public interface MUserMapper {

    MUser findEmail(@Param("email") String email);

    MUser find(@Param("user_id") int userId);
    
    int insert(MUser mUser);

}
