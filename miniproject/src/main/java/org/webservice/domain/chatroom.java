package org.webservice.domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

import lombok.Data;

@Data
public class chatroom {
	String chatroom_code; //"랜덤 UUID"_"채팅방제목"_"날짜"
	String chatroom_title;
	String regid;
	Date regdate;
	
	Set<WebSocketSession> sessionSet=new HashSet<>();//연결 가져올때 chatroomconnect 서비스를 활용
	
}
