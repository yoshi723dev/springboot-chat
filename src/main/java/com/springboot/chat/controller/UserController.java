package com.springboot.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.chat.controller.model.GetFriendResponse;
import com.springboot.chat.controller.model.GetFriendResponse.Friends;
import com.springboot.chat.controller.model.LoginRequest;
import com.springboot.chat.controller.model.RegistFriendRequest;
import com.springboot.chat.entity.MUser;
import com.springboot.chat.entity.TFriend;
import com.springboot.chat.logic.UserLogic;
import com.springboot.chat.mapper.MUserMapper;
import com.springboot.chat.mapper.TFriendMapper;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private TFriendMapper friendMapper;
	
	@Autowired
	private MUserMapper userMapper;
	
	@Autowired
	private UserLogic userLogic;
	
	@PostMapping( path="/login")
	public String login(@RequestBody LoginRequest request) throws Exception {
		// 一度セッションを削除する
		session.removeAttribute("user");
		
		MUser mUser = userMapper.find(request.getUserId());
		if (mUser == null || !mUser.getPassword().equals(request.getPassword())) {
			throw new Exception("login error");
		}
		
		session.setAttribute("user", mUser);
		return "OK";
	}
	
	@GetMapping(path="/get_friend")
	public GetFriendResponse getFriendList() throws Exception {
		// セッションからユーザ情報を取得
		MUser mUser = (MUser) session.getAttribute("user");
		if (mUser == null) {
			throw new Exception("no session");
		}
		
		List<TFriend> tFriend = friendMapper.find(mUser.getUser_id());
		GetFriendResponse response = new GetFriendResponse();
		Friends[] listFriends = new Friends[tFriend.size()];
		for (int i=0; i<tFriend.size(); i++) {
			Friends friends = response.new Friends();
			MUser user = userMapper.find(tFriend.get(i).getFriend_user_id());
			friends.setUserId(user.getUser_id());
			friends.setUserNm(user.getUser_nm());
			listFriends[i] = friends;
		}
		response.setFriends(listFriends);
		return response;
	}
	
	@PostMapping(path="/regist_friend")
	public String registFriend(@RequestBody RegistFriendRequest request) throws Exception {
		// セッションからユーザ情報を取得
		MUser mUser = (MUser) session.getAttribute("user");
		if (mUser == null) {
			throw new Exception("no session");
		}
		
		try {
			userLogic.registFriend(mUser.getUser_id(), request.getFriendIds());
			return "OK";
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return "NG";
		}
	}
	
	
	//deleteFriend
	//registUser
}
