package org.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.webservice.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,String>{

}
