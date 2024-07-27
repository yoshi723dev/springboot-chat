package com.springboot.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.chat.controller.model.ChatLogResponse;
import com.springboot.chat.controller.model.ChatLogResponse.ChatLog;
import com.springboot.chat.controller.model.GetChatGroupResponse;
import com.springboot.chat.controller.model.GetChatGroupResponse.ChatGroup;
import com.springboot.chat.controller.model.GetChatLogRequest;
import com.springboot.chat.controller.model.RegistChatLogRequest;
import com.springboot.chat.controller.model.RegistChatLogResponse;
import com.springboot.chat.entity.MUser;
import com.springboot.chat.logic.ChatLogic;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/chat")
public class ChatController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ChatLogic chatLogic;
	
	@GetMapping(path="/get_chatgrouplist")
	public GetChatGroupResponse getChatGroupList() {
		// セッションからユーザ情報を取得
		MUser mUser = (MUser) session.getAttribute("user");
		
		// 画面から受け取ったchatgroupidが存在するか、存在しない場合新規採番
		ChatGroup[] aryChatGroup = chatLogic.getChatGroupList(mUser.getUser_id());
		
		GetChatGroupResponse response = new GetChatGroupResponse();
		response.setListChatGroup(aryChatGroup);
		return response;
	}
	
	@PostMapping(path="/redirect_chat")
	public String redirectChat(@RequestBody GetChatLogRequest request) {
		// セッションにリクエスト情報退避
		session.setAttribute("chatinfo", request);

		return "chat.html";
	}
	
	@GetMapping(path="/get_chatlog")
	public ChatLogResponse getChatLog() throws Exception {
		// セッションからユーザ情報を取得
		MUser mUser = (MUser) session.getAttribute("user");
		
		// セッションから値を取得
		if (session.getAttribute("chatinfo") == null) {
			throw new Exception("no session");
		}
		GetChatLogRequest request = (GetChatLogRequest) session.getAttribute("chatinfo");
		
		// 画面から受け取ったchatgroupidが存在するか、存在しない場合新規採番
		int newChatGroupId = chatLogic.checkChatGroupId(request.getChatGroupId(), mUser.getUser_id(), request.getFriendUserIds());
		
		// チャットログ取得
		ChatLog[] aryChats = chatLogic.getChatLog(newChatGroupId);
		ChatLogResponse response = new ChatLogResponse();
		response.setChatGroupId(newChatGroupId);
		response.setChatLog(aryChats);
		
		// セッションクリア
		session.removeAttribute("chatinfo");
		
		return response;
	}
	
	@PostMapping(path="/regist_chatlog")
	public RegistChatLogResponse registChatLog(@RequestBody RegistChatLogRequest request) {
		// セッションからユーザ情報を取得
		MUser mUser = (MUser) session.getAttribute("user");
		
		// 画面から受け取ったchatgroupidが存在するか、存在しない場合新規採番
		int newChatGroupId = chatLogic.checkChatGroupId(request.getChatGroupId(), mUser.getUser_id(), null);
		
		chatLogic.setChatLog(newChatGroupId, mUser.getUser_id(), request.getMessage());
		
		return new RegistChatLogResponse();
	}
}
