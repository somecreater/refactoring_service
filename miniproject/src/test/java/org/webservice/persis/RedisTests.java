package org.webservice.persis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testRedisConnection() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        String key = "testKey";
        String value = "testValue";
        valueOperations.set(key, value);

        String storedValue = valueOperations.get(key);

        assertThat(storedValue).isEqualTo(value);
    }
}
