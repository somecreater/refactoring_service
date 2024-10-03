package org.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.webservice.entity.AttachFileEntity;

@Repository
public interface AttachFileRepository extends JpaRepository<AttachFileEntity,String> {

}
