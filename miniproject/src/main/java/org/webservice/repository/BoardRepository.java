package org.webservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.webservice.entity.BanEntity;
import org.webservice.entity.BoardEntity;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
        List<BoardEntity> findByTitleContaining(String title, Pageable pageable);
        List<BoardEntity> findByWriterContaining(String writer, Pageable pageable);
        List<BoardEntity> findByBordcategoryContaining(String Bordcategory, Pageable pageable);
        List<BoardEntity> findByContentContaining(String content, Pageable pageable);
}
