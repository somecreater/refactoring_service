package org.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.webservice.entity.AuthEntity;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity,String> {

}
