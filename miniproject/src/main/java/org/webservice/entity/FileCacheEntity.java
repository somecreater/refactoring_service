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

    //검색용(게시글 하나에 업로드 되는 모든 파일은 동일한 값을 가지고 있음)
    //랜덤 uuid_업로드 날짜, 추후 수정(임시)
    private String file_cache_group_id;
    private String file_uuid;
    private String userid;
    private String fileorgname;
    private String path;
    private long size;
    //{text,image,pdf,hwp,etc}
    private String filetype;

}
