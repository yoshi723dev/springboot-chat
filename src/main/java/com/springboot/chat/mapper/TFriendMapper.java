package com.springboot.chat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.chat.entity.TFriend;

@Mapper
public interface TFriendMapper {

    List<TFriend> find(@Param("user_id") int user_id);

    int insert(TFriend tFriend);

}
