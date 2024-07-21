package com.springboot.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.chat.controller.model.ChatLogResponse;
import com.springboot.chat.controller.model.ChatLogResponse.Chats;
import com.springboot.chat.entity.MUser;
import com.springboot.chat.entity.TChatLog;
import com.springboot.chat.mapper.MUserMapper;
import com.springboot.chat.mapper.TChatLogMapper;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/chat")
public class ChatController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private MUserMapper userMapper;
	
	@Autowired
	private TChatLogMapper chatLogMapper;
	
	@GetMapping( path="/get_chatlog")
	public ChatLogResponse getChatLog(int frend_user_id) {
		MUser mUser = (MUser) session.getAttribute("user");
		int minId = Math.min(mUser.getUser_id(), frend_user_id);
		int maxId = Math.max(mUser.getUser_id(), frend_user_id);
		String chatGroupId = String.valueOf(minId) + "_" + String.valueOf(maxId);
		List<TChatLog> listChat = chatLogMapper.findOrder(chatGroupId);
		ChatLogResponse response = new ChatLogResponse();
		Chats[] listChats = new Chats[listChat.size()];
		for (int i=0; i<listChat.size(); i++) {
			Chats chats = response.new Chats();
			chats.setUser_id(listChat.get(i).getUser_id());
			chats.setChat_date(listChat.get(i).getChat_date());
			chats.setComment(listChat.get(i).getComment());
			listChats[i] = chats;
		}
		response.setChats(listChats);
		
		return response;
	}
	
	//registChatLog
}
