package org.webservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.webservice.entity.BanEntity;
import org.webservice.entity.ChatroomEntity;
import org.webservice.entity.CommentEntity;

import java.util.List;

@Repository
public interface ChatroomRepository extends JpaRepository<ChatroomEntity,String> {
        List<ChatroomEntity> findByRegidContaining(String regid, Pageable pageable);
        List<ChatroomEntity> findByChatroomtitleContaining(String chatroomtitle, Pageable pageable);
}
