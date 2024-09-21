package org.webservice.websocket;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.webservice.domain.chatmessage;
import org.webservice.service_1.communicationservice;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Component
@RequiredArgsConstructor
public class websockethandler extends TextWebSocketHandler{
	
	//웹소켓 연결 객체를 저장하는 리스트
	List<WebSocketSession> sessionlist=new ArrayList<WebSocketSession>();
	final ObjectMapper objectmapper; 
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println(session.getUri());
		
		sessionlist.add(session);
		super.afterConnectionEstablished(session);
		System.out.println("새로 생성된 세션 아이디: "+session.getId());
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionlist.remove(session);
		System.out.println(session.getId()+" 연결 세션 종료");
		super.afterConnectionClosed(session, status);
		
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg=message.getPayload();
		System.out.println(msg);
		String[] msgform=msg.split("\n");
		String msgjson=msgform[4];
		if (msg.startsWith("CONNECT")) {
	        return;
	    }
		chatmessage chatmsg=objectmapper.readValue(msgjson, chatmessage.class);
		
		System.out.println(chatmsg.getContent());
		for(WebSocketSession s: sessionlist) {
			if(!s.getId().equals(session.getId())) {
				s.sendMessage(new TextMessage(objectmapper.writeValueAsString(chatmsg)));
			}
		}
		super.handleTextMessage(session,message);
	}
	
}
