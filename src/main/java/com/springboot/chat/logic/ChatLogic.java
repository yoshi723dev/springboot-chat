package com.springboot.chat.logic;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.chat.controller.model.GetChatGroupResponse;
import com.springboot.chat.controller.model.GetChatGroupResponse.ChatGroup;
import com.springboot.chat.controller.model.GetChatLogResponse;
import com.springboot.chat.controller.model.GetChatLogResponse.ChatLog;
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
	
	/**
	 * ユーザのチャットグループの一覧を返す.
	 * 
	 * @param userId
	 * @return
	 */
	public ChatGroup[] getChatGroupList(int userId) {
		try {
			List<TChatGroup> listChatGroupByUserId = chatGroupMapper.findUserChatGroup(userId);
			ChatGroup[] aryChatGroup = new ChatGroup[listChatGroupByUserId.size()];
			for (int i=0; i<listChatGroupByUserId.size(); i++) {
				String chatName = "";
				String delimiter = "";
				List<TChatGroup> listTChatGroupByGroupId = chatGroupMapper.find(listChatGroupByUserId.get(i).getChat_group_id());
				// 自分を含まないため、チャット参加者-1
				int[] friendIds = new int[listTChatGroupByGroupId.size()-1];
				int friendCnt = 0;
				for (int z=0; z<listTChatGroupByGroupId.size(); z++) {
					if (userId == listTChatGroupByGroupId.get(z).getUser_id()) {
						continue;
					}
					MUser mUser = mUserMapper.find(listTChatGroupByGroupId.get(z).getUser_id());
					chatName += delimiter + mUser.getUser_nm();
					delimiter = "と";
					friendIds[friendCnt] = mUser.getUser_id();
					friendCnt++;
				}
				chatName += "の会話";
				
				ChatGroup chatGroup = new GetChatGroupResponse().new ChatGroup();
				chatGroup.setChatGroupId(listChatGroupByUserId.get(i).getChat_group_id());
				chatGroup.setChatName(chatName);
				chatGroup.setFriend_user_ids(friendIds);
				aryChatGroup[i] = chatGroup;
			}
			return aryChatGroup;
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * ユーザのチャットグループをチェックする。ない場合は新規作成.
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional
	public int checkChatGroupIdNottingCreate(int chatGroupId, int userId, String friendUserIds) {
		int newChatGroupId = chatGroupId;
		try {
			// 画面から受け取ったchatgroupidが存在するか
			List<TChatGroup> listTChatGroup = chatGroupMapper.find(chatGroupId);
			// ない場合、新規のチャット
			if (listTChatGroup == null || listTChatGroup.size() == 0) {
				// チャット情報が何もない場合は1、それ以外は最大値+1
				newChatGroupId = chatGroupMapper.findMax();
				if (newChatGroupId == 0) {
					newChatGroupId = 1;
				} else {
					newChatGroupId++;
				}
				// ユーザのchatgroupを登録
				chatGroupMapper.insert(newChatGroupId, userId);
			}
			// friendUserIdが指定されている場合、友達のレコードも登録する
			if (friendUserIds != null) {
				int[] intFriendUserIds = Stream.of(friendUserIds.split(",")).mapToInt(Integer::parseInt).toArray();
				for (int i=0; i<intFriendUserIds.length; i++) {
					TChatGroup tChatGroup = chatGroupMapper.findUserId(chatGroupId, intFriendUserIds[i]);
					if (tChatGroup == null) {
						chatGroupMapper.insert(newChatGroupId, intFriendUserIds[i]);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return newChatGroupId;
	}
	
	/**
	 * 特定ユーザとの1:1のチャットグループIDを返す。ない場合は0.
	 * 
	 * @param myUserId
	 * @param friendUserId
	 * @return
	 */
	public int getChatGroupIdForFriend(int myUserId, int friendUserId) {
		List<TChatGroup> listChatGroupByUserId = chatGroupMapper.findUserChatGroup(myUserId);
		for (TChatGroup tChatGroup : listChatGroupByUserId) {
			List<TChatGroup> listTChatGroupByGroupId = chatGroupMapper.find(tChatGroup.getChat_group_id());
			for (TChatGroup tChatGroupByGroupId : listTChatGroupByGroupId) {
				if (tChatGroupByGroupId.getUser_id() != friendUserId) {
					continue;
				}
				if (listTChatGroupByGroupId.size() == 2) {
					return tChatGroup.getChat_group_id();
				}
			}

		}
		return 0;

	}
	
	
	/**
	 * チャットグループのチャットログを返す.
	 * 
	 * @param chatGroupId
	 * @param myUserId
	 * @return
	 */
	public ChatLog[] getChatLog(int chatGroupId, int myUserId) {
		List<TChatLog> listChat = chatLogMapper.findOrder(chatGroupId);
		ChatLog[] listChats = new ChatLog[listChat.size()];
		Map<Integer, String> mapUser = new HashMap<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i=0; i<listChat.size(); i++) {
			ChatLog chatLog = new GetChatLogResponse().new ChatLog();
			int myMessage = 0;
			if (myUserId == listChat.get(i).getUser_id()) {
				myMessage = 1;
			}
			if (! mapUser.containsKey(listChat.get(i).getUser_id())) {
				MUser mUser = mUserMapper.find(listChat.get(i).getUser_id());
				mapUser.put(mUser.getUser_id(), mUser.getUser_nm());
			}
	        String strDate = formatter.format(listChat.get(i).getChat_date());
			chatLog.setMy_message(myMessage);
			chatLog.setUser_nm(mapUser.get(listChat.get(i).getUser_id()));
			chatLog.setChat_date(strDate);
			chatLog.setComment(listChat.get(i).getComment());
			listChats[i] = chatLog;
		}
		return listChats;
	}
	
	/**
	 * チャットの発言を登録する.
	 * 
	 * @param chatGroupId
	 * @param userId
	 * @param message
	 */
	@Transactional
	public void setChatLog(int chatGroupId, int userId, String message) {
		TChatLog tChatLog = new TChatLog();
		tChatLog.setChat_group_id(chatGroupId);
		tChatLog.setUser_id(userId);
		tChatLog.setComment(message);
		chatLogMapper.insert(tChatLog);
	}
}
