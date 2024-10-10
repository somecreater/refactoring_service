package org.webservice.persis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.webservice.entity.BoardCacheEntity;
import org.webservice.redis_repository.BoardCacheRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RedisTests {

    /*
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    */
    @Autowired
    private BoardCacheRepository boardCacheRepository;

    //@Test
    /*
    public void testRedisConnection() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        String key = "testKey";
        String value = "testValue";
        valueOperations.set(key, value);

        String storedValue = valueOperations.get(key);

        assertThat(storedValue).isEqualTo(value);
    }
    */

    @Test
    public void testRedisRepository(){
        BoardCacheEntity boardCache=BoardCacheEntity.builder().bno(1L).viscount(12).recommendation(12).build();
        boardCacheRepository.save(boardCache);

        BoardCacheEntity boardCache1=boardCacheRepository.findById(1L).get();
        assertThat(boardCache1.getViscount()).isEqualTo(boardCache.getViscount());
        assertThat(boardCache1.getRecommendation()).isEqualTo(boardCache.getRecommendation());
    }

}
