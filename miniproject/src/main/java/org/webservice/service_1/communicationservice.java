package org.webservice.service_1;

import java.util.List;

import org.webservice.domain.chatroom;
import org.webservice.domain.friend;

public interface communicationservice {
	
	public List<friend> getlistfriend(String userid);
	public void insert_friend(String userid, String fuserid);
	public void delete_friend(String userid, String fuserid);
	
	public List<chatroom> getlistchatroom();
	public chatroom selectchatroom(String code);
	public chatroom createchatroom(String title);
	public void deletechatroom(String code);
	public void deleteallchatroom();
	
}
