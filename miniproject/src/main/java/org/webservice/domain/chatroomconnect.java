package org.webservice.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class chatroomconnect {
	String connect_code; //"채팅방 코드"_"유저의 아이디"
	String userid;
	String session_id; // 직접 웹소켓 세션의 아이디를 저장
	String chatroom_code;
}
