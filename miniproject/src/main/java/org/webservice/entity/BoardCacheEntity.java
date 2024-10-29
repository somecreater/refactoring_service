package org.webservice.entity;

import lombok.Setter;
import org.springframework.data.annotation.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@Builder
@RedisHash(value = "board_cache")
public class BoardCacheEntity {
    @Id
    private Long bno;
    private long viscount;
    private int recommendation;
    public BoardCacheEntity(Long bno, long viscount, int recommendation){
        this.bno=bno;
        this.viscount=viscount;
        this.recommendation=recommendation;
    }
    public  BoardCacheEntity(){

    }
}
