package org.webservice.redis_repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.webservice.entity.FileCacheEntity;

@Repository
public interface FileCacheRepository extends CrudRepository<FileCacheEntity,String> {
}
