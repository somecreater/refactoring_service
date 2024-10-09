package org.webservice.redis_repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.webservice.entity.BoardCacheEntity;

@Repository
public interface BoardCacheRepository extends CrudRepository<BoardCacheEntity,Long> {
}
