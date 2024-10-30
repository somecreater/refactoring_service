package org.webservice.refactoring_service;

import org.springframework.web.multipart.MultipartFile;
import org.webservice.Innerdto.SearchDTO;
import org.webservice.entity.BoardEntity;

import java.util.List;

public interface BoardService {
    //board 읽기, 입력, 수정, 삭제
    public BoardEntity ReadBoard(Long bno);
    public boolean InsertBoard(BoardEntity board, MultipartFile[] multipartFiles);
    public boolean UpdateBoard(BoardEntity board, MultipartFile[] multipartFiles);
    public boolean DeleteBoard(Long bno);

    //입력된 게시물에 대한 유효성 검사
    public boolean CheckBoard(BoardEntity board);

    //board 조회수와 추천수 업데이트(일정한 주기로 MySQL DB에 반영)
    public void UpdateViewCount(BoardEntity boardEntity);
    public boolean UpdateLikeCount(BoardEntity boardEntity);

    //board 리스트 읽어오기 및 검색
    public List<BoardEntity> SearchBoard(SearchDTO Search);
}
