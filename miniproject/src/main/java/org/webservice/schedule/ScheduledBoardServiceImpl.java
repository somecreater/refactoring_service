package org.webservice.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public void BoardCacheUpdate(){

        Iterable<BoardCacheEntity> cachelist=boardCacheRepository.findAll();
        List<Long> bnolist = new ArrayList<>();
        for (BoardCacheEntity cache : cachelist) {
            bnolist.add(cache.getBno());
        }

        List<BoardEntity> boardEntities=boardRepository.findAllById(bnolist);
        Map<Long, BoardEntity> boardEntityMap=new HashMap<>();
        for(BoardEntity boardEntity:boardEntities){
           boardEntityMap.put(boardEntity.getBno(),boardEntity);
        }

        redisTemplate.executePipelined((RedisCallback<Object>)connection ->{

            for(BoardCacheEntity boardCacheEntity:cachelist){
                Long bno=boardCacheEntity.getBno();
                int recommendation=boardCacheEntity.getRecommendation();
                long viscount=boardCacheEntity.getViscount();

                BoardEntity boardEntity=boardEntityMap.get(bno);
                if(boardEntity!=null){
                    boardEntity.setViscount(boardEntity.getViscount()+viscount);
                    boardEntity.setRecommendation(boardEntity.getRecommendation()+recommendation);
                    boardEntityMap.replace(bno,boardEntity);
                }
            }

            boardCacheRepository.deleteAll(cachelist);
            return null;
        });

        if(!boardEntityMap.isEmpty()){
            boardRepository.saveAll(boardEntityMap.values());
        }
    }

}
