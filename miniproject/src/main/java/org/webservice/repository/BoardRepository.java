package org.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webservice.entity.BanEntity;

public interface BoardRepository extends JpaRepository<BanEntity,Object> {
}
