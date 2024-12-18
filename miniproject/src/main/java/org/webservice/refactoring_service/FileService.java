package org.webservice.refactoring_service;


import org.springframework.web.multipart.MultipartFile;
import org.webservice.Innerdto.FileDTO;
import org.webservice.Innerdto.SearchDTO;
import org.webservice.entity.AttachFileEntity;
import org.webservice.entity.FileCacheEntity;

import java.util.List;

public interface FileService {
    //파일 업로드, 삭제
    public List<FileDTO> MakeFileDTO(MultipartFile[] filelist);
    public boolean UploadFile(MultipartFile[] file, Long bno, String TempBoardId) ;
    public boolean DeleteFile(Long bno);
    public List<AttachFileEntity> SearchFile(SearchDTO Search);
    public boolean CheckFile(MultipartFile file);

    //추가 구현 필요, 게시글 입력창에서 파일 업로드 시
    //임시로 저장할 곳 필요(redis, 별개의 파일 저장소 활용)
    public List<FileCacheEntity> UploadTempFile(MultipartFile[] FileList,String TempBoardId);
}
