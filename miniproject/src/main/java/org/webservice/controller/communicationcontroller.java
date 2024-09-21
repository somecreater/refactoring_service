package org.webservice.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.webservice.domain.chatmessage;
import org.webservice.domain.chatroom;
import org.webservice.domain.friend;
import org.webservice.service_1.communicationservice;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@Log4j
public class communicationcontroller {

	@Setter(onMethod_=@Autowired)
	private communicationservice communicateservice;

	private final SimpMessagingTemplate simpmessageingtemplate;
	
	//채팅방에 들어간다.
	@PreAuthorize("authenticated()")
	@GetMapping("chat")
	public void chatting(String code, Model model) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String exuserid=auth.getName();
		chatroom chtroom=communicateservice.selectchatroom(code);
		String title=chtroom.getChatroom_title();
		String reguserid=chtroom.getRegid();
		
		model.addAttribute("reguser", reguserid);
		model.addAttribute("chatroomtitle",title);
		model.addAttribute("myid", exuserid);
		model.addAttribute("chatroomcode", code);
	}

	//채팅 메세지를 전송한다
	//채팅 보내는 유저의 실제 아이디와 객체 내에 저장된 아이디 확인
	@PreAuthorize("principal.username ==  #chat.userid")
	@MessageMapping("/chat/message/{code}")
	public void sendchat(@DestinationVariable String code, chatmessage chat) {
		simpmessageingtemplate.convertAndSend("/sub/chat/"+code,chat);
		log.info("채팅 유저: "+chat.getUserid()+", 채팅방 코드: "+chat.getRoomcode()+", 채팅 내용: "+chat.getContent()+", 채팅 유형: "+chat.getType()+", 채팅 일자: "+chat.getRegdate());
	}
	
	//친구를 삭제한다.
	@PreAuthorize("authenticated()")
	@PostMapping("/deletefriend")
	@ResponseBody
	public Map<String,Object> deletefriend(String userid, String fuserid){

		Map<String, Object> response=new HashMap<String, Object>();
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String exuserid=auth.getName();
		if(exuserid.compareTo(userid)!=0){
			response.put("result", "failure");
		}else {
			communicateservice.delete_friend(exuserid, fuserid);
			response.put("result", "success");
		}	
		return response;
	}
	
	//친구를 추가한다.
	@PreAuthorize("authenticated()")
	@PostMapping("/insertfriend")
	@ResponseBody
	public Map<String,Object> insertfriend(String userid, String fuserid){

		Map<String, Object> response=new HashMap<String, Object>();
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String exuserid=auth.getName();
		if(exuserid.compareTo(userid)!=0){
			response.put("result", "failure");
		}else {
			communicateservice.insert_friend(userid, fuserid);
			response.put("result", "success");
		}	
		return response;
	}

	//채팅방 리스트를 가져온다
	@PreAuthorize("authenticated()")
	@GetMapping("/chatlist")
	@ResponseBody
	public Map<String,Object> getchatlist(){
		Map<String, Object> response=new HashMap<String, Object>();
		List<chatroom> chtlist=communicateservice.getlistchatroom();
		response.put("chatlist", chtlist);
		
		return response;
	}

	//채팅방의 존재여부를 검증한다.
	@PreAuthorize("authenticated()")
	@GetMapping("/chatverification")
	@ResponseBody
	public Map<String,Object> validchat(String code){
		Map<String, Object> response=new HashMap<String, Object>();
		if(communicateservice.selectchatroom(code)!=null) {
			response.put("result", "success");
		}else {
			response.put("result", "failure");
		}
		return response;
	}
	
	//채팅방을 생성한다.
	@PreAuthorize("authenticated()")
	@PostMapping("/chatcreation")
	@ResponseBody
	public  Map<String,Object> createchatroom(String title) {
		//채팅방 정보를 db에 넣는다. 그러면 바로 생성된 채팅방으로 접속
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String exuserid=auth.getName();
		Map<String, Object> response=new HashMap<String, Object>();
		System.out.println("채팅방 제목: "+title);
		chatroom chrroom=communicateservice.createchatroom(title);
		String code=chrroom.getChatroom_code();
		response.put("chatcode", code);
		response.put("user", exuserid);
		return response;
	}
	
	//채팅방을 삭제한다.
	@PreAuthorize("authenticated()")
	@PostMapping("/chatdelete")
	@ResponseBody
	public  Map<String,Object> deletechatroom(String code) {
		Map<String, Object> response=new HashMap<String, Object>();
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String exuserid=auth.getName();
		chatroom chat=communicateservice.selectchatroom(code);
		if(chat.getRegid().compareTo(exuserid)!=0) {
			response.put("result","failure");
		}else if(chat.getRegid().compareTo(exuserid)==0){
			chatmessage chtexit=new chatmessage();
			chtexit.setRoomcode(code);
			chtexit.setContent("chatroom_remove");
			chtexit.setType("removechatroom");
			chtexit.setUserid(chat.getRegid());
			simpmessageingtemplate.convertAndSend("/sub/chat/"+code,chtexit);
			//이 방법 말고도 벡엔드 단에서 채팅방이 사라질시 자동으로 세션이 끊기고 창이 닫히게 하는 방법을 찾아보자
			
			
			communicateservice.deletechatroom(code);
			response.put("result","success");
		}else {
			response.put("result","failure");
		}
		return response;
	}
	
}
