package org.webservice.refactoring_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.webservice.Innerdto.SearchDTO;
import org.webservice.entity.BoardEntity;
import org.webservice.repository.BoardRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private FileService fileService;

    @Override
    public BoardEntity ReadBoard(Long bno) {
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(bno);
        return boardEntityOptional.orElse(null);
    }

    @Transactional
    @Override
    public boolean InsertBoard(BoardEntity board, MultipartFile[] multipartFiles) {
        boolean result;
        try{
            Long newBno = boardRepository.save(board).getBno();
            if (multipartFiles.length!=0) {
                fileService.UploadFile(multipartFiles,newBno);
            }
            result=true;
        }catch (Exception e){
            log.info("게시물 저장 과정에서 오류 발생!!");
            result=false;
        }

        return result;
    }

    @Transactional
    @Override
    public boolean UpdateBoard(BoardEntity board, MultipartFile[] multipartFiles) {
        return false;
    }

    @Transactional
    @Override
    public boolean DeleteBoard(Long bno) {
        return false;
    }

    @Override
    public List<BoardEntity> SearchBoard(SearchDTO Search) {
        return List.of();
    }
}
