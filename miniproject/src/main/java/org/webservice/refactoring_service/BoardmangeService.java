package org.webservice.refactoring_service;

import org.webservice.entity.AuthEntity;
import org.webservice.entity.BoardTypeEntity;

public interface BoardmangeService {

    //boardType 생성, 수정, 읽기, 삭제
    public boolean CreateBoardType(BoardTypeEntity boardTypeEntity);
    public boolean UpdateBoardType(BoardTypeEntity boardTypeEntity);
    public BoardTypeEntity readBoardType(String BoardName);
    public boolean DeleteBoardType(BoardTypeEntity boardTypeEntity);

    //boardType에 대한 권한 추가, 삭제
    public boolean InsertBoardAuth(AuthEntity authEntity);
    public boolean DeleteBoardAUth(String UserId,String Auth);

}
