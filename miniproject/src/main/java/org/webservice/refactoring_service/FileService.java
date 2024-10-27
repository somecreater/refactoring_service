package org.webservice.refactoring_service;


import org.webservice.Innerdto.SearchDTO;
import org.webservice.entity.AttachFileEntity;

import java.util.List;

public interface FileService {
    //파일 업로드, 삭제
    public boolean UploadFile(AttachFileEntity fileEntity);
    public boolean DeleteFile(AttachFileEntity fileEntity);
    public List<AttachFileEntity> SearchFile(SearchDTO Search);
}
