package com.springboot.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.chat.controller.model.GetFriendResponse;
import com.springboot.chat.controller.model.GetFriendResponse.Friends;
import com.springboot.chat.controller.model.LoginRequest;
import com.springboot.chat.entity.MUser;
import com.springboot.chat.entity.TFriend;
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
	
	@PostMapping( path="/login", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public boolean login(LoginRequest login) {
		MUser mUser = userMapper.findOne(login.getUser_id());
		if (mUser.getPassword().equals(login.getPassword())) {
			session.setAttribute("user", mUser);
			return true;
		}
		return false;
	}
	
	@GetMapping(path="/get_friend")
	public GetFriendResponse getFrendList(int user_id) {
		List<TFriend> tFriend = friendMapper.find(user_id);
		GetFriendResponse response = new GetFriendResponse();
		Friends[] listFrends = new Friends[tFriend.size()];
		for (int i=0; i<tFriend.size(); i++) {
			Friends frends = response.new Friends();
			MUser user = userMapper.findOne(tFriend.get(i).getFriend_user_id());
			frends.setUser_id(user.getUser_id());
			frends.setUser_nm(user.getUser_nm());
			listFrends[i] = frends;
		}
		response.setFriends(listFrends);
		return response;
	}
	
	//registFrend
	//deleteFrend
	//registUser
}
