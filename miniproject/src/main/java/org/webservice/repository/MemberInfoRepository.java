package org.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webservice.entity.BanEntity;
import org.webservice.entity.MemberInfoEntity;

public interface MemberInfoRepository extends JpaRepository<MemberInfoEntity,Object> {

}
