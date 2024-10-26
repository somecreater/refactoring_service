package org.webservice.refactoring_service;

import org.apache.http.entity.FileEntity;

public interface FileService {
    //파일 업로드, 삭제
    public boolean UploadFile(FileEntity fileEntity);
    public boolean DeleteFIle(FileEntity fileEntity);
}
