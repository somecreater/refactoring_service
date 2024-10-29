package org.webservice.refactoring_service;

import org.webservice.Innerdto.SearchDTO;
import org.webservice.entity.CommentEntity;

import java.util.List;

public class CommentServiceImpl implements CommentService{
    @Override
    public boolean InsertComment(CommentEntity commentEntity) {
        return false;
    }

    @Override
    public List<CommentEntity> ReadCommentList(Long bno) {
        return List.of();
    }

    @Override
    public boolean UpdateComment(CommentEntity commentEntity) {
        return false;
    }

    @Override
    public boolean DeleteComment(Long rno) {
        return false;
    }

    @Override
    public boolean SearchComment(SearchDTO search) {
        return false;
    }
}
