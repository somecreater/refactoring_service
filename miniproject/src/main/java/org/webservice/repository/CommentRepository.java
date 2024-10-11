package org.webservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.webservice.entity.BanEntity;
import org.webservice.entity.CommentEntity;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
        List<CommentEntity> findByWriterContaining(String writer, Pageable pageable);
        List<CommentEntity> findByBnoContaining(Long bno, Pageable pageable);
        List<CommentEntity> findByCommentsContaining(String comments, Pageable pageable);
}
