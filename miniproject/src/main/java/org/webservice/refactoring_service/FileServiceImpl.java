package org.webservice.refactoring_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.webservice.Innerdto.FileDTO;
import org.webservice.Innerdto.SearchDTO;
import org.webservice.entity.AttachFileEntity;
import org.webservice.entity.FileCacheEntity;
import org.webservice.redis_repository.FileCacheRepository;
import org.webservice.repository.AttachFileRepository;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService{

    @Autowired
    private AttachFileRepository attachFileRepository;
    @Autowired
    private FileCacheRepository fileCacheRepository;
    @Value("${file.upload.temp-dir}")
    private String TempPath;

    @Override
    public List<FileDTO> MakeFileDTO(MultipartFile[] filelist){

        //파일 정보를 저장하는 객체 리스트 생성
        List<FileDTO> fileDTOS=new ArrayList<>();
        for(MultipartFile multipartFile:filelist){
            FileDTO fileDTO=new FileDTO();
            fileDTO.setFileOrgName(multipartFile.getName());
            fileDTO.setSize(multipartFile.getSize());
            fileDTO.setPath("");
            fileDTO.setFileType("");
            fileDTOS.add(fileDTO);
        }
        return fileDTOS;
    }

    //임시로 업로드된 파일을 MySQL DB에 등록하고, 실제 폴더에 업로드하는 메소드
    @Transactional
    @Override
    public boolean UploadFile(MultipartFile[] file, Long bno, String TempBoardId) {
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
    public boolean DeleteFile(Long bno) {
        attachFileRepository.deleteByBno(bno);
        return false;
    }

    @Override
    public List<AttachFileEntity> SearchFile(SearchDTO Search) {
        return List.of();
    }

    @Override
    public boolean CheckFile(MultipartFile file) {
        //일단 임시(확장자만 검사하거나 외부의 api 활용?)
        return true;
    }

    //게시글 등록과정에서 업로드된 파일과 파일 정보를 redis Database, 임시 폴더에 저장
    public List<FileCacheEntity> UploadTempFile(MultipartFile[] FileList,String TempBoardId){
        List<FileCacheEntity> fileCacheEntities=new ArrayList<>();
        String fileGroupId= TempBoardId;
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String Uploader= auth.getName();

        try {
            for (MultipartFile file : FileList) {
                if (CheckFile(file)) {
                    String fileOrgName = file.getName();
                    String fileUUID = UUID.randomUUID().toString();
                    String fileUUidName = fileUUID + "_" + fileOrgName;
                    String filePath = TempPath + fileGroupId + "\\" + fileUUidName;
                    String fileCacheId=fileGroupId+"_"+fileUUidName;
                    File tempfile=new File(filePath);
                    file.transferTo(tempfile);
                    long filesize = file.getSize();
                    String type = Files.probeContentType(tempfile.toPath());
                    FileCacheEntity fileCacheEntity=new FileCacheEntity(fileCacheId
                            ,fileGroupId,Uploader,fileUUID,fileOrgName,fileUUidName,
                            filePath,filesize,type);
                    fileCacheRepository.save(fileCacheEntity);
                }

            }
        }
        catch (Exception e){
            log.info("파일 등록 과정에서 문제 발생");
        }
        return fileCacheEntities;
    }
}
