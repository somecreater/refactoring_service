package org.webservice.refactoring_service;

import org.webservice.entity.ChatroomEntity;

public interface ChatroomService {

    //채팅방 생성, 삭제만 존재
    public boolean CreateChatroom(ChatroomEntity chatroom);
    public boolean DeleteChatroom(String chatname, String userid);


}
