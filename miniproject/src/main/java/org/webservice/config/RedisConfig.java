package org.webservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

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
        LettuceConnectionFactory lettuceConnectionFactory=new LettuceConnectionFactory(redisStandaloneConfiguration);
        return lettuceConnectionFactory;
    }




}
