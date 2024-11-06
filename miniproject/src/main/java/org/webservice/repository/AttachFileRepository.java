package org.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.webservice.entity.AttachFileEntity;

import java.util.List;

@Repository
public interface AttachFileRepository extends JpaRepository<AttachFileEntity,String> {
    List<AttachFileEntity> findByBno(Long bno);
    List<AttachFileEntity> findByFiletypeContaining(String filetype);
    List<AttachFileEntity> findByFilenameContaining(String filename);
    void deleteByBno(Long bno);
}
