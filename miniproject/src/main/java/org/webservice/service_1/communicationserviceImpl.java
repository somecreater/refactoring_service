package org.webservice.service_1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webservice.domain.chatroom;
import org.webservice.domain.friend;
import org.webservice.mapper.friendmapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class communicationserviceImpl implements communicationservice{


	@Setter(onMethod_ = @Autowired)
	private friendmapper frdmapper;
	
	@Override
	public List<friend> getlistfriend(String userid) {

		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String exuserid=auth.getName();
		if(exuserid.compareTo(userid)!=0) {
			return null;
		}else {
			List<friend> frdlist=frdmapper.listfriend(userid);
			return frdlist;
		}
		
	}

	@Override
	public void insert_friend(String userid, String fuserid) {
		
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String exuserid=auth.getName();
		if(exuserid.compareTo(userid)!=0) {
			return;
		}
		else {
			friend frd=new friend();
			String frd_code="~Friend~"+userid+"~"+fuserid;
			frd.setFriend_code(frd_code);
			frd.setFuserid(fuserid);
			frd.setUserid(userid);
			frdmapper.insertfriend(frd);
		}
		
	}

	@Override
	public void delete_friend(String userid, String fuserid) {
		
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String exuserid=auth.getName();
		if(exuserid.compareTo(userid)!=0) {
			return;
		}
		else {
			friend frd=new friend();
			String frd_code="~Friend~"+userid+"~"+fuserid;
			frd.setFriend_code(frd_code);
			frd.setFuserid(fuserid);
			frd.setUserid(userid);
			frdmapper.deletefriend(frd);
			
		}
	}

	@Override
	public List<chatroom> getlistchatroom(){
		List<chatroom> chatlist=frdmapper.allchatroom();
		return chatlist;
	}
	@Override
	public chatroom selectchatroom(String code) {
		return frdmapper.getchatroom(code);
	}
	@Override
	public chatroom createchatroom(String title) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String exuserid=auth.getName();
		
		//만약 해당 유저 이름으로 만든 채팅방이 5개 존재하면 채팅방을 만들지 않고 null을 반환
		chatroom chtroom=new chatroom();
		UUID uuid=UUID.randomUUID();
		Date datevalue=new Date();
		String createdate=new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(datevalue);
		String code=uuid+"_"+title+"_"+createdate;
		
		chtroom.setChatroom_code(code);
		chtroom.setChatroom_title(title);
		chtroom.setRegid(exuserid);
		frdmapper.createroom(chtroom);
		
		return chtroom;
	};
	@Override
	public void deletechatroom(String code) {
		chatroom chtroom=frdmapper.getchatroom(code);
		frdmapper.deleteroom(chtroom);
	};
	
	@Override
	public void deleteallchatroom() {
		frdmapper.deleteallroom();
	}
}
