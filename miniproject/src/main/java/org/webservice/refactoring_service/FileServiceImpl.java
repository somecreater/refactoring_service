package org.webservice.refactoring_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.webservice.Innerdto.FileDTO;
import org.webservice.Innerdto.SearchDTO;
import org.webservice.entity.AttachFileEntity;
import org.webservice.repository.AttachFileRepository;

import java.util.List;

@Service
@Slf4j
public class FileServiceImpl implements FileService{

    @Autowired
    private AttachFileRepository attachFileRepository;

    @Override
    public FileDTO MakeFileDTO(MultipartFile file){

        return new FileDTO();
    }

    @Transactional
    @Override
    public boolean UploadFile(MultipartFile[] file, Long bno) {
        try{
                for(MultipartFile multipartFile:file){
                if(CheckFile(multipartFile)){
                    //파일 저장, db 데이터 저장 로직
                }else{
                    throw new Exception();
                }
            }
        }catch (Exception e){
            log.info("{} 번 게시물의 파일에 문제가 있습니다.",bno);
            return false;
        }

        return true;
    }

    @Transactional
    @Override
    public boolean DeleteFile(AttachFileEntity fileEntity) {
        return false;
    }

    @Override
    public List<AttachFileEntity> SearchFile(SearchDTO Search) {
        return List.of();
    }

    @Override
    public boolean CheckFile(MultipartFile file) {

        return false;
    }
}
