package org.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webservice.entity.AttachFileEntity;

public interface AttachFileRepository extends JpaRepository<AttachFileEntity,String> {
}
