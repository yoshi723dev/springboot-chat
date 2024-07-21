package com.springboot.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.chat.controller.model.GetFrendResponse;
import com.springboot.chat.controller.model.GetFrendResponse.Frends;
import com.springboot.chat.controller.model.LoginRequest;
import com.springboot.chat.entity.MUser;
import com.springboot.chat.entity.TFrend;
import com.springboot.chat.mapper.MUserMapper;
import com.springboot.chat.mapper.TFrendMapper;

@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	private TFrendMapper frendMapper;
	
	@Autowired
	private MUserMapper userMapper;
	
	@PostMapping( path="/login", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public boolean login(LoginRequest login) {
		MUser mUser = userMapper.findOne(login.getUser_id());
		if (mUser.getPassword().equals(login.getPassword())) {
			return true;
		}
		return false;
	}
	
	@GetMapping(path="/get_frend")
	public GetFrendResponse getFrendList(int user_id) {
		List<TFrend> tFrend = frendMapper.find(user_id);
		GetFrendResponse response = new GetFrendResponse();
		Frends[] listFrends = new Frends[tFrend.size()];
		for (int i=0; i<tFrend.size(); i++) {
			Frends frends = response.new Frends();
			MUser user = userMapper.findOne(tFrend.get(i).getFrend_user_id());
			frends.setUser_id(user.getUser_id());
			frends.setUser_nm(user.getUser_nm());
			listFrends[i] = frends;
		}
		response.setFrends(listFrends);
		return response;
	}
	
	//registFrend
	//deleteFrend
	//registUser
}
