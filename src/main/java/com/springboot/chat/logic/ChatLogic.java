package com.springboot.chat.logic;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.chat.controller.model.ChatLogResponse;
import com.springboot.chat.controller.model.ChatLogResponse.ChatLog;
import com.springboot.chat.controller.model.GetChatGroupResponse;
import com.springboot.chat.controller.model.GetChatGroupResponse.ChatGroup;
import com.springboot.chat.entity.MUser;
import com.springboot.chat.entity.TChatGroup;
import com.springboot.chat.entity.TChatLog;
import com.springboot.chat.mapper.MUserMapper;
import com.springboot.chat.mapper.TChatGroupMapper;
import com.springboot.chat.mapper.TChatLogMapper;

@Component
public class ChatLogic {
	
	@Autowired
	private MUserMapper mUserMapper;
	
	@Autowired
	private TChatLogMapper chatLogMapper;
	
	@Autowired
	private TChatGroupMapper chatGroupMapper;
	
	public ChatGroup[] getChatGroupList(int userId) {
		List<TChatGroup> listChatGroupByUserId = chatGroupMapper.findUserChatGroup(userId);
		ChatGroup[] aryChatGroup = new ChatGroup[listChatGroupByUserId.size()];
		for (int i=0; i<listChatGroupByUserId.size(); i++) {
			String chatName = "";
			String delimiter = "";
			List<TChatGroup> listTChatGroupByGroupId = chatGroupMapper.find(listChatGroupByUserId.get(i).getChat_group_id());
			int[] friendIds = new int[listTChatGroupByGroupId.size()];
			for (int z=0; z<listTChatGroupByGroupId.size(); z++) {
				if (userId == listTChatGroupByGroupId.get(z).getUser_id()) {
					continue;
				}
				MUser mUser = mUserMapper.find(listTChatGroupByGroupId.get(z).getUser_id());
				chatName += delimiter + mUser.getUser_nm();
				delimiter = "と";
				friendIds[z] = mUser.getUser_id();
			}
			chatName += "の会話";
			
			ChatGroup chatGroup = new GetChatGroupResponse().new ChatGroup();
			chatGroup.setChatGroupId(listChatGroupByUserId.get(i).getChat_group_id());
			chatGroup.setChatName(chatName);
			chatGroup.setFriend_user_ids(friendIds);
			aryChatGroup[i] = chatGroup;
		}
		return aryChatGroup;
	}
	
	@Transactional
	public int checkChatGroupId(int chatGroupId, int userId, String friendUserIds) {
		int newChatGroupId = chatGroupId;
		int[] intFriendUserIds = Stream.of(friendUserIds.split(",")).mapToInt(Integer::parseInt).toArray();
		try {
			// 画面から受け取ったchatgroupidが存在するか
			List<TChatGroup> listTChatGroup = chatGroupMapper.find(chatGroupId);
			// ない場合、新規のチャット
			if (listTChatGroup == null || listTChatGroup.size() == 0) {
				// ユーザのchatgroupを登録
				newChatGroupId = chatGroupMapper.findMax();
				if (newChatGroupId == 0) {
					newChatGroupId = 1;
				}
				chatGroupMapper.insert(chatGroupId, userId);
			}
			// friendUserIdが指定されている場合、他社のレコードも登録する
			if (friendUserIds != null) {
				for (int i=0; i<intFriendUserIds.length; i++) {
					TChatGroup tChatGroup = chatGroupMapper.findUserId(chatGroupId, intFriendUserIds[i]);
					if (tChatGroup == null) {
						chatGroupMapper.insert(chatGroupId, intFriendUserIds[i]);
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
	
	@Transactional
	public void setChatLog(int chatGroupId, int userId, String message) {
		TChatLog tChatLog = new TChatLog();
		tChatLog.setChat_group_id(chatGroupId);
		tChatLog.setUser_id(userId);
		tChatLog.setComment(message);
		chatLogMapper.insert(tChatLog);
	}
}
