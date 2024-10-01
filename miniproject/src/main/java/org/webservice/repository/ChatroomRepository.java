package org.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webservice.entity.BanEntity;

public interface ChatroomRepository extends JpaRepository<BanEntity,Object> {
}
