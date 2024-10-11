package org.webservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.webservice.entity.BanEntity;

import java.util.List;

@Repository
public interface BanRepository extends JpaRepository<BanEntity,Long> {
    List<BanEntity> findByUseridContaining(String userid, Pageable pageable);
}
