package org.webservice.refactoring_service;


import org.springframework.web.multipart.MultipartFile;
import org.webservice.Innerdto.FileDTO;
import org.webservice.Innerdto.SearchDTO;
import org.webservice.entity.AttachFileEntity;

import java.util.List;

public interface FileService {
    //파일 업로드, 삭제
    public FileDTO MakeFileDTO(MultipartFile file);
    public boolean UploadFile(MultipartFile[] file,Long bno);
    public boolean DeleteFile(AttachFileEntity fileEntity);
    public List<AttachFileEntity> SearchFile(SearchDTO Search);
    public boolean CheckFile(MultipartFile file);
}
