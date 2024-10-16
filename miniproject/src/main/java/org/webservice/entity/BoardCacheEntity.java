package org.webservice.entity;

import org.springframework.data.annotation.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@RedisHash(value = "board_cache")
public class BoardCacheEntity {
    @Id
    private Long bno;
    private long viscount;
    private int recommendation;
}
