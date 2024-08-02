package com.springboot.chat.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.chat.controller.model.GetChatGroupResponse;
import com.springboot.chat.controller.model.GetChatGroupResponse.ChatGroup;
import com.springboot.chat.controller.model.GetChatLogRequest;
import com.springboot.chat.controller.model.GetChatLogResponse;
import com.springboot.chat.controller.model.GetChatLogResponse.ChatLog;
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
    
    /**
     * ユーザのチャットグループ一覧を返す.
     * 
     * @return
     * @throws Exception
     */
    @GetMapping(path="/get_chatgrouplist")
    public GetChatGroupResponse getChatGroupList() throws Exception {
        // セッションからユーザ情報を取得
        MUser mUser = (MUser) session.getAttribute("user");
        
        // 画面から受け取ったchatgroupidが存在するか、存在しない場合新規採番
        ChatGroup[] aryChatGroup = chatLogic.getChatGroupList(mUser.getUser_id());
        
        GetChatGroupResponse response = new GetChatGroupResponse();
        response.setListChatGroup(aryChatGroup);
        return response;
    }
    
    /**
     * チャット情報を受け取り、チャット画面へ遷移する.
     * 
     * @return
     * @throws URISyntaxException 
     * @throws Exception
     */
    @PostMapping(path="/redirect_chat")
    public String redirectChat(@RequestBody GetChatLogRequest request) throws URISyntaxException {
        // セッションにリクエスト情報退避
        session.setAttribute("chatinfo", request);
        
        //効かない
        //return "redirect:http://localhost:8080/chat.html";
        //Response response = Response.seeOther(new URI("/chat.html")).build();
        return "/chat.html";
    }
    
    /**
     * チャットの情報を返す.
     * 
     * @return
     * @throws Exception
     */
    @GetMapping(path="/get_chatlog")
    public GetChatLogResponse getChatLog() throws Exception {
        // セッションからユーザ情報を取得
        MUser mUser = (MUser) session.getAttribute("user");
        // セッションから値を取得
        GetChatLogRequest request = (GetChatLogRequest) session.getAttribute("chatinfo");
        if (request == null) {
            throw new Exception("no session");
        }
        
        // 画面から受け取ったchatgroupidが存在するか、存在しない場合新規採番
        int newChatGroupId = chatLogic.checkChatGroupIdNottingCreate(
                request.getChatGroupId(), mUser.getUser_id(), request.getFriendUserIds());
        
        // チャットログ取得
        ChatLog[] aryChats = chatLogic.getChatLog(newChatGroupId, mUser.getUser_id());
        GetChatLogResponse response = new GetChatLogResponse();
        response.setChatGroupId(newChatGroupId);
        response.setChatLog(aryChats);
        
        return response;
    }
    
    /**
     * チャットの発言を登録する.
     * 
     * @return
     * @throws Exception
     */
    @PostMapping(path="/regist_chatlog")
    public RegistChatLogResponse registChatLog(
            @RequestBody @Validated RegistChatLogRequest request, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            throw new Exception("input error");
        }
        // セッションからユーザ情報を取得
        MUser mUser = (MUser) session.getAttribute("user");
        
        // 画面から受け取ったchatgroupidが存在するか、存在しない場合新規採番
        int newChatGroupId = chatLogic.checkChatGroupIdNottingCreate(request.getChatGroupId(), mUser.getUser_id(), null);
        
        chatLogic.setChatLog(newChatGroupId, mUser.getUser_id(), request.getMessage());
        
        return new RegistChatLogResponse();
    }
}
