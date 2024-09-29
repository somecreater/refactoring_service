package org.webservice.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.webservice.entity.MemberEntity;

import java.util.Collection;

public class Refactoring_Customuserdetail extends User {
    public Refactoring_Customuserdetail(MemberEntity memberentity, Collection<? extends GrantedAuthority> authorities){
        super(memberentity.getUsername(),memberentity.getUserpw(), memberentity.isEnabled(),
                true,true,true,authorities);
    }

}
