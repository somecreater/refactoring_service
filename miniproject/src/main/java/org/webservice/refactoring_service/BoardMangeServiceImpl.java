package org.webservice.refactoring_service;

import org.webservice.entity.AuthEntity;
import org.webservice.entity.BoardTypeEntity;

public class BoardMangeServiceImpl implements BoardMangeService{
    @Override
    public BoardTypeEntity MakeBoardType(String BoardName, String BoardSubject) {
        return null;
    }

    @Override
    public boolean CreateBoardType(BoardTypeEntity boardTypeEntity) {
        return false;
    }

    @Override
    public boolean UpdateBoardType(BoardTypeEntity boardTypeEntity) {
        return false;
    }

    @Override
    public BoardTypeEntity readBoardType(String BoardName) {
        return null;
    }

    @Override
    public boolean DeleteBoardType(BoardTypeEntity boardTypeEntity) {
        return false;
    }

    @Override
    public boolean InsertBoardAuth(AuthEntity authEntity) {
        return false;
    }

    @Override
    public boolean DeleteBoardAUth(String UserId, String Auth) {
        return false;
    }
}
