package org.webservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.webservice.entity.BanEntity;
import org.webservice.entity.ChatroomEntity;

import java.util.List;

@Repository
public interface ChatroomRepository extends JpaRepository<BanEntity,String> {
        List<ChatroomEntity> findByRegidcontaining(String regid, Pageable pageable);
        List<ChatroomEntity> findByChatroom_titlecontaining(String chatroom_title, Pageable pageable);
}
