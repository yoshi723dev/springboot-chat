package com.springboot.chat.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.chat.controller.model.ChatLogResponse;
import com.springboot.chat.controller.model.ChatLogResponse.ChatLog;
import com.springboot.chat.entity.TChatGroup;
import com.springboot.chat.entity.TChatLog;
import com.springboot.chat.mapper.TChatGroupMapper;
import com.springboot.chat.mapper.TChatLogMapper;

@Component
public class ChatLogic {
	
	@Autowired
	private TChatLogMapper chatLogMapper;
	
	@Autowired
	private TChatGroupMapper chatGroupMapper;
	
	@Transactional
	public int checkChatGroupId(int chatGroupId, int userId, int[] friendUserIds) {
		int newChatGroupId = chatGroupId;
		try {
			// 画面から受け取ったchatgroupidが存在するか
			TChatGroup tChatGroup = chatGroupMapper.find(chatGroupId);
			// ない場合、新規のチャット
			if (tChatGroup == null) {
				// ユーザのchatgroupを登録
				newChatGroupId = chatGroupMapper.findMax();
				if (newChatGroupId == 0) {
					newChatGroupId = 1;
				}
				chatGroupMapper.insert(chatGroupId, userId);
			}
			// friendUserIdが指定されている場合、他社のレコードも登録する
			if (friendUserIds != null) {
				for (int i=0; i<friendUserIds.length; i++) {
					tChatGroup = chatGroupMapper.findUserId(chatGroupId, friendUserIds[i]);
					if (tChatGroup == null) {
						chatGroupMapper.insert(chatGroupId, friendUserIds[i]);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return newChatGroupId;
	}
	
	public ChatLog[] getChatLog(int chatGroupId) {
		List<TChatLog> listChat = chatLogMapper.findOrder(chatGroupId);
		ChatLog[] listChats = new ChatLog[listChat.size()];
		for (int i=0; i<listChat.size(); i++) {
			ChatLog chatLog = new ChatLogResponse().new ChatLog();
			chatLog.setUser_id(listChat.get(i).getUser_id());
			chatLog.setChat_date(listChat.get(i).getChat_date());
			chatLog.setComment(listChat.get(i).getComment());
			listChats[i] = chatLog;
		}
		return listChats;
	}
	
	public void setChatLog(int chatGroupId, int userId, String message) {
		TChatLog tChatLog = new TChatLog();
		tChatLog.setChat_group_id(chatGroupId);
		tChatLog.setUser_id(userId);
		tChatLog.setComment(message);
		chatLogMapper.insert(tChatLog);
	}
}
