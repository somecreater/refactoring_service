package org.webservice.redis_repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.webservice.entity.PermissionEntity;

@Repository
public interface PermissionRepository extends CrudRepository<PermissionEntity,Long> {
}
