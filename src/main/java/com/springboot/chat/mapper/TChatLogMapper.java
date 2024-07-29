package com.springboot.chat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.chat.entity.TChatLog;

@Mapper
public interface TChatLogMapper {

    List<TChatLog> findOrder(@Param("chat_group_id") int chat_group_id);

    int insert(TChatLog tChatLog);

}
