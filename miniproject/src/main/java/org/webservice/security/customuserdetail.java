package org.webservice.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.webservice.domain.member;

public class customuserdetail extends User{

	public customuserdetail(member mem, Collection<? extends GrantedAuthority> authorities) {
		super(mem.getUserid(), mem.getUserpw(), mem.isEnabled(), true, true, true, authorities);
	}

}
