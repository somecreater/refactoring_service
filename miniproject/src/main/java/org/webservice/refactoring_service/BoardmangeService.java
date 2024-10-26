package org.webservice.refactoring_service;

import org.webservice.entity.BoardTypeEntity;

public interface BoardmangeService {

    //boardType 생성, 수정, 읽기, 삭제
    public boolean CreateBoardType();
    public boolean UpdateBoardType();
    public BoardTypeEntity readBoardType();
    public boolean DeleteBoardType();

    //boardType에 대한 권한 추가, 삭제
    public boolean InsertBoardAuth();
    public boolean DeleteBoardAUth();
}
