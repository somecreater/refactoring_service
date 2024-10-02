package org.webservice.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import org.webservice.entity.AuthEntity;
import org.webservice.entity.MemberEntity;
import org.webservice.repository.AuthRepository;
import org.webservice.repository.MemberRepository;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
public class Refactoring_Customdetailservice implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AuthRepository authRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
    {
        MemberEntity member=memberRepository.getReferenceById(username);
        Optional<AuthEntity> optionalauth = authRepository.findById(member.getUserid());
        String memberauth = optionalauth.map(AuthEntity::getAuth).orElse("common");

        //boardmanager 테이블에서 게시판 관련 권한을 가져오는 코드도 포함 예정
        Refactoring_Customuserdetail customuserdetail
                = new Refactoring_Customuserdetail(member, AuthorityUtils.createAuthorityList(memberauth));
        log.info("login UserID: {}, TIME: {}", username, LocalDate.now());

        return customuserdetail;
    }
}
