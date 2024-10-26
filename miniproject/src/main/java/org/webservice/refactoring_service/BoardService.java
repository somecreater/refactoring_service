package org.webservice.refactoring_service;

import org.webservice.entity.BoardEntity;

import java.util.List;

public interface BoardService {
    //board 읽기, 입력, 수정, 삭제
    public BoardEntity ReadBoard(Long bno);
    public boolean InsertBoard(BoardEntity board);
    public boolean UpdateBoard(BoardEntity board);
    public boolean DeleteBoard(Long bno);

    //board 리스트 읽어오기 및 검색
    public List<BoardEntity> SearchBoard(String Search);


    //조회수 업데이트, 추천수 업데이트
    public boolean UpdateViscount(Long bno);
    public boolean UpdateRecommendation(Long bno);

}
