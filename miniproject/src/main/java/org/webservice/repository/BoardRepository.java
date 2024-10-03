package org.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.webservice.entity.BanEntity;
import org.webservice.entity.BoardEntity;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BanEntity,Long> {
        List<BoardEntity> findByTitleContaining(String title);
        List<BoardEntity> findByWriterContaining(String writer);
        List<BoardEntity> findByBoardtype(String boardtype);
}
