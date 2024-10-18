package org.webservice.persis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.webservice.entity.BoardCacheEntity;
import org.webservice.entity.PermissionEntity;
import org.webservice.redis_repository.BoardCacheRepository;
import org.webservice.redis_repository.PermissionRepository;

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
    @Autowired
    private PermissionRepository permissionRepository;
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

    //@Test
    public void testRedisRepository(){
        BoardCacheEntity boardCache=BoardCacheEntity.builder().bno(20L).viscount(1355).recommendation(80).build();
        boardCacheRepository.save(boardCache);

        BoardCacheEntity boardCache1=boardCacheRepository.findById(100L).get();
        System.out.println(boardCache1.getViscount());
        System.out.println(boardCache1.getRecommendation());
        //assertThat(boardCache1.getViscount()).isEqualTo(boardCache.getViscount());
        //assertThat(boardCache1.getRecommendation()).isEqualTo(boardCache.getRecommendation());
    }

    @Test
    public void testRedisRepository2(){
        PermissionEntity permission=PermissionEntity.builder().alarmid(11L).userid("tester").content("this is test").alarmtype("test").build();
        permissionRepository.save(permission);

        PermissionEntity permission1=permissionRepository.findById(11L).get();
        System.out.println(permission1.getAlarmtype());
        System.out.println(permission1.getContent());
    }

}
