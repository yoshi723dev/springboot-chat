package com.springboot.chat.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.chat.entity.TFriend;
import com.springboot.chat.mapper.TFriendMapper;

@Component
public class UserLogic {
	
	@Autowired
	private TFriendMapper friendMapper;
	
	/**
	 * 友達登録.(手が回らず未使用)
	 * 
	 * @param userId
	 * @param friendUserIds
	 */
	@Transactional
	public void registFriend(int userId, int[] friendUserIds) {
		try {
			List<TFriend> listFriend = friendMapper.find(userId);
			Map<Integer, TFriend> mapFriend = new HashMap<>();
			for (TFriend tFriend : listFriend) {
				mapFriend.put(tFriend.getFriend_user_id(), tFriend);
			}

			for (int i=0; i<friendUserIds.length; i++) {
				if (! mapFriend.containsKey(friendUserIds[i])) {
					TFriend tFriend = new TFriend();
					tFriend.setUser_id(userId);
					tFriend.setFriend_user_id(friendUserIds[i]);
					friendMapper.insert(tFriend);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
