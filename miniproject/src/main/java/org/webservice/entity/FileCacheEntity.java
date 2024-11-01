package org.webservice.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@RedisHash(value = "file_cache")
public class FileCacheEntity {
    //redis 내에 임시로 파일 정보 저장

    @Id
    //파일 그룹 아이디+번호
    private String file_cache_id;

    //검색용(게시글 하나에 업로드 되는 모든 파일은 동일한 값을 가지고 있음)
    //랜덤 uuid_업로드 날짜, 추후 수정(임시)
    private String file_cache_group_id;

    private String userid;
    private String file_uuid;
    private String fileorgname;
    private String fileuuidname;

    //임시 파일 경로 정보: C:/temp/uploads/file_cache_group_id/
    private String path;

    private long size;
    //{text,image,pdf,hwp,etc}
    private String filetype;

    public FileCacheEntity(String file_cache_id,String file_cache_group_id,
                           String userid,String file_uuid,String fileorgname,
                           String fileuuidname,String path,long size, String filetype){
        this.file_cache_id=file_cache_id;
        this.file_cache_group_id=file_cache_group_id;
        this.userid=userid;
        this.file_uuid=file_uuid;
        this.fileorgname=fileorgname;
        this.fileuuidname=fileuuidname;
        this.path=path;
        this.size=size;
        this.filetype=filetype;
    }

    public FileCacheEntity(){}

}
