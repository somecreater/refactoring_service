package org.webservice.refactoring_service;

import org.webservice.entity.CommentEntity;

public interface CommentService {
    //comment 입력, 읽기, 수정, 삭제
    public boolean InsertComment(CommentEntity commentEntity);
    public CommentEntity ReadComment(Long bno);
    public boolean UpdateComment(CommentEntity commentEntity);
    public boolean DeleteComment(Long rno);

    //comment 검색
    public boolean SearchComment(String search);
}
