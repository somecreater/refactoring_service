package org.webservice.refactoring_service;

import org.webservice.Innerdto.SearchDTO;
import org.webservice.entity.CommentEntity;

import java.util.List;

public interface CommentService {
    //comment 입력, 읽기, 수정, 삭제
    public boolean InsertComment(CommentEntity commentEntity);
    public List<CommentEntity> ReadCommentList(Long bno);
    public boolean UpdateComment(CommentEntity commentEntity);
    public boolean DeleteComment(Long rno);

    //comment 검색
    public boolean SearchComment(SearchDTO search);
}
