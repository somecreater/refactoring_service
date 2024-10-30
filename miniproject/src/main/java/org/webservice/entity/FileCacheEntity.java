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
    private Long file_cache_id;
    private String file_uuid;
    private String userid;
    private String fileorgname;
    private String path;
    private long size;
    //{text,image,pdf,hwp,etc}
    private String filetype;

}
