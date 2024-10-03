package org.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.webservice.entity.BoardManagerEntity;

@Repository
public interface BoardManagerRepository extends JpaRepository<BoardManagerEntity,Long> {

}
