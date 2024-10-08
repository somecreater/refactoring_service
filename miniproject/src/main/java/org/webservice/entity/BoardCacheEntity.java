package org.webservice.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@RedisHash(value = "board_cache",timeToLive = 20)
public class BoardCacheEntity {
    @Id
    private Long bno;
    private long viscount;
    private int recommendation;
}
