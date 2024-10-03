package org.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.webservice.entity.BanEntity;

@Repository
public interface CommentRepository extends JpaRepository<BanEntity,Long> {

}
