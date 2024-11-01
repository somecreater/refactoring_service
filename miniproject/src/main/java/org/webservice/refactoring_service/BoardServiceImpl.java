package org.webservice.refactoring_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.webservice.Innerdto.SearchDTO;
import org.webservice.entity.BoardCacheEntity;
import org.webservice.entity.BoardEntity;
import org.webservice.redis_repository.BoardCacheRepository;
import org.webservice.repository.BoardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardCacheRepository boardCacheRepository;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private FileService fileService;
    private final String Key="board_cache:";

    @Override
    public BoardEntity ReadBoard(Long bno) {
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(bno);

        if(boardEntityOptional.orElse(null)!=null) {
            try {
                UpdateViewCount(boardEntityOptional.get());
            } catch (Exception e) {
                log.info("{} 번 게시물의 조회수 업데이트 실패", bno);
            }
        }

        return boardEntityOptional.orElse(null);
    }

    //수정 필요
    @Transactional
    @Override
    public boolean InsertBoard(BoardEntity board, String TempBoardId, MultipartFile[] multipartFiles) {
        boolean result;
        try{
            Long newBno = boardRepository.save(board).getBno();
            if (multipartFiles.length!=0) {
                fileService.UploadFile(multipartFiles,newBno,TempBoardId);
            }
            result=true;
        }catch (Exception e){
            log.info("게시물 저장 과정에서 오류 발생!!");
            result=false;
        }

        return result;
    }

    //수정 필요
    @Transactional
    @Override
    public boolean UpdateBoard(BoardEntity board, String TempBoardId,MultipartFile[] multipartFiles) {
        try {
            if(CheckBoard(board)) {
                boardRepository.save(board);
                if(multipartFiles.length!=0){
                    fileService.UploadFile(multipartFiles,board.getBno(),TempBoardId);
                }
            }else{
                return false;
            }
        }catch (Exception e){
            log.info("{} 번 게시물 수정 과정에서 오류 발생!!",board.getBno());
            return false;
        }
        return true;
    }

    //파일 삭제 구현 필요
    @Transactional
    @Override
    public boolean DeleteBoard(Long bno) {
        try{
            String realKey=Key+bno+"*";
            redisTemplate.delete(realKey);
            fileService.DeleteFile(bno);
            boardRepository.deleteById(bno);

        }catch (Exception e){
            log.info("{} 번 게시글 삭제 과정에서 오류 발생",bno);
            return false;
        }
        return true;
    }

    //게시물의 필수 요소중 하나로도 누락되면 등록 불가 처리
    public boolean CheckBoard(BoardEntity board){
        return board.getBno() != null &&
                board.getBordcategory() != null &&
                board.getContent() != null &&
                board.getTitle() != null;
    }

    @Transactional
    @Override
    public void UpdateViewCount(BoardEntity boardEntity){

        String realKey=Key+boardEntity.getBno()+":viscount";
        if (redisTemplate.opsForValue().get(realKey) == null) {
            BoardCacheEntity boardCacheEntity=new BoardCacheEntity(boardEntity.getBno(),1, 0);
            boardCacheRepository.save(boardCacheEntity);
        }else{
            redisTemplate.opsForValue().increment(realKey, 1);
        }
    }

    @Transactional
    @Override
    public boolean UpdateLikeCount(BoardEntity boardEntity){
        try {
            String realKey=Key+boardEntity.getBno()+":recommendation";
            if (redisTemplate.opsForValue().get(realKey) == null) {
                BoardCacheEntity boardCacheEntity=new BoardCacheEntity(boardEntity.getBno(),0, 1);
                boardCacheRepository.save(boardCacheEntity);
            }else{
                redisTemplate.opsForValue().increment(realKey, 1);
            }

        }catch(Exception e){
            log.info("{} 번 게시글 추천수 업데이트 과정에서 오류 발생",boardEntity.getBno());
            return false;
        }
        return true;
    }

    @Override
    public List<BoardEntity> SearchBoard(SearchDTO Search, Pageable pageable) {
        List<BoardEntity> boardEntityList=new ArrayList<>();
        String datatype=Search.getDataType();
        String searchtype=Search.getSearchType();
        String content=Search.getContent();
        if(datatype.compareTo("board")==0) {
            switch (searchtype) {
                case "title":
                    boardEntityList=boardRepository.findByTitleContaining(content,pageable);
                    break;
                case "content":
                    boardEntityList=boardRepository.findByContentContaining(content,pageable);
                    break;
                case "boardtype":
                    boardEntityList=boardRepository.findByBordcategoryContaining(content,pageable);
                    break;
                case "userid":
                    boardEntityList=boardRepository.findByWriterContaining(content,pageable);
                    break;
            }
        }
        return boardEntityList;
    }
}
