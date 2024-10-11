package org.webservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
@Configuration
@EnableRedisRepositories(basePackages = {"org.webservice.redis_repository"})
@EnableConfigurationProperties({RedisProperty.class})
public class RedisConfig {

    private final RedisProperty redisProperty;

    public RedisConfig(RedisProperty redisProperty) {
        this.redisProperty = redisProperty;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(RedisProperty redisProperty){
        RedisStandaloneConfiguration redisStandaloneConfiguration=new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisProperty.getHost());
        redisStandaloneConfiguration.setPort(redisProperty.getPort());
        redisStandaloneConfiguration.setPassword(redisProperty.getPassword());

        log.info("Redis에 연결 중: {}:{}", redisProperty.getHost(), redisProperty.getPort());

        LettuceConnectionFactory lettuceConnectionFactory=new LettuceConnectionFactory(redisStandaloneConfiguration);
        return lettuceConnectionFactory;
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory(redisProperty));
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());

    return template;
    }
}
