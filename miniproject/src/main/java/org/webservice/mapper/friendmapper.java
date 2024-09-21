package org.webservice.mapper;

import java.util.List;

import org.webservice.domain.chatroom;
import org.webservice.domain.friend;

public interface friendmapper {
	public List<friend> listfriend(String userid);
	public void insertfriend(friend frd);
	public void deletefriend(friend frd);
	
	public void createroom(chatroom room);
	public void deleteroom(chatroom room);
	public void deleteallroom();
	public List<chatroom> allchatroom();
	public chatroom getchatroom(String chatroom_code);
}
