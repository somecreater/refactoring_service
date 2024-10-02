package org.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webservice.entity.BoardTypeEntity;

public interface BoardTypeRepository extends JpaRepository<BoardTypeEntity,Long> {
}
