package com.springboot.chat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.chat.entity.TFrend;

@Mapper
public interface TFrendMapper {

	List<TFrend> find(@Param("user_id") int user_id);

	int insert(TFrend tFrend);

}
