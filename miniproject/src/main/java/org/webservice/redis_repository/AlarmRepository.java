package org.webservice.redis_repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.webservice.entity.AlarmCacheEntity;

@Repository
public interface AlarmRepository extends CrudRepository<AlarmCacheEntity,Long> {
}
