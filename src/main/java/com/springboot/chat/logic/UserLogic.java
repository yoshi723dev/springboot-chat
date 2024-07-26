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
	
	@Transactional
	public void registFriend(int userId, int[] friendUserIds) {
		try {
			List<TFriend> listFriend = friendMapper.find(userId);
			Map<Integer, TFriend> mapFriend = new HashMap<>();
			for (TFriend tFriend : listFriend) {
				mapFriend.put(tFriend.getFriendUserId(), tFriend);
			}

			for (int i=0; i<friendUserIds.length; i++) {
				if (! mapFriend.containsKey(friendUserIds[i])) {
					TFriend tFriend = new TFriend();
					tFriend.setUserId(userId);
					tFriend.setFriendUserId(friendUserIds[i]);
					friendMapper.insert(tFriend);
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
