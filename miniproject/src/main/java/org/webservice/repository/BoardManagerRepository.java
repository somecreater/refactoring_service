package org.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webservice.entity.BoardManagerEntity;

public interface BoardManagerRepository extends JpaRepository<BoardManagerEntity,Long> {
}
