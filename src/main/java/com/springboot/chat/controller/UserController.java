package com.springboot.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.chat.controller.model.GetFriendResponse;
import com.springboot.chat.controller.model.GetFriendResponse.Friends;
import com.springboot.chat.controller.model.LoginRequest;
import com.springboot.chat.controller.model.LoginResponse;
import com.springboot.chat.entity.MUser;
import com.springboot.chat.entity.TFriend;
import com.springboot.chat.logic.ChatLogic;
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
    private ChatLogic chatLogic;
    
    /**
     * ログイン.
     * 
     * @param request
     * @param result
     * @return
     * @throws Exception
     */
    @PostMapping( path="/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            throw new Exception("input error");
        }
        // 一度セッションを削除する
        session.removeAttribute("user");
        
        MUser mUser = userMapper.find(request.getUserId());
        // パスワードが生情報なので設計変更
        if (mUser == null || !mUser.getPassword().equals(request.getPassword())) {
            throw new Exception("login error");
        }
        
        session.setAttribute("user", mUser);
        return new LoginResponse();
    }
    
    /**
     * 友達情報を返す.
     * 
     * @param request
     * @param result
     * @return
     * @throws Exception
     */
    @GetMapping(path="/get_friend")
    public GetFriendResponse getFriendList() throws Exception {
        // セッションからユーザ情報を取得
        MUser mUser = (MUser) session.getAttribute("user");
        
        List<TFriend> tFriend = friendMapper.find(mUser.getUser_id());
        GetFriendResponse response = new GetFriendResponse();
        Friends[] listFriends = new Friends[tFriend.size()];
        for (int i=0; i<tFriend.size(); i++) {
            Friends friends = response.new Friends();
            MUser friend = userMapper.find(tFriend.get(i).getFriend_user_id());
            int chatGroupId = chatLogic.getChatGroupIdForFriend(mUser.getUser_id(), friend.getUser_id());
            
            friends.setUserId(friend.getUser_id());
            friends.setUserNm(friend.getUser_nm());
            friends.setChatGroupId(chatGroupId);
            listFriends[i] = friends;
        }
        response.setFriends(listFriends);
        return response;
    }
}
