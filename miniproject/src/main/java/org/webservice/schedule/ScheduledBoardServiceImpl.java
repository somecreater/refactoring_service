package org.webservice.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.webservice.entity.BoardCacheEntity;
import org.webservice.entity.BoardEntity;
import org.webservice.redis_repository.BoardCacheRepository;
import org.webservice.repository.BoardRepository;

import java.util.*;

@Slf4j
public class ScheduledBoardServiceImpl implements ScheduledBoardService{

    @Autowired
    private BoardCacheRepository boardCacheRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    @Scheduled(fixedRate = 10000)
    public void BoardCacheUpdate(){

        Iterable<BoardCacheEntity> cachelist=boardCacheRepository.findAll();
        List<BoardEntity> boardEntities=new ArrayList<>();

        redisTemplate.executePipelined((RedisCallback<Object>)connection ->{

            for(BoardCacheEntity boardCacheEntity:cachelist){
                Long bno=boardCacheEntity.getBno();
                BoardEntity boardEntity=boardRepository.findById(bno).orElse(null);
                int recommendation=boardCacheEntity.getRecommendation();
                long viscount=boardCacheEntity.getViscount();
                if(boardEntity!=null){
                    boardEntity.setViscount(boardEntity.getViscount()+viscount);
                    boardEntity.setRecommendation(boardEntity.getRecommendation()+recommendation);
                    //추후구현

                }
            }

            return null;
        });
    }

}
