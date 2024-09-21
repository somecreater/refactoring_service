package org.webservice.security;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.webservice.domain.auth;
import org.webservice.domain.member;
import org.webservice.mapper.membermapper;

import lombok.extern.log4j.Log4j;

@Log4j
public class customuserdetailservice2 implements UserDetailsService{

	@Autowired
	membermapper mmapper;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		member mems=mmapper.readmember(username);
		String existrole="";
		
		List<auth> auli=new ArrayList<auth>();
		ArrayList<String> aulistr = new ArrayList<String>();
		customuserdetail cususer;
		auli=mmapper.readauth(username);
		
		if(auli!=null) {
			for(int i=0;i<auli.size();i++) {
				existrole=auli.get(i).getAuth();
				aulistr.add(existrole);
			}
			
		
		String[] aul=aulistr.toArray(new String[aulistr.size()]);
		cususer=new customuserdetail(mems, AuthorityUtils.createAuthorityList(aul));
		}
		else {
			auli=new ArrayList<auth>();
			auth a=new auth();
			a.setUserid(username);
			a.setAuth("common");
			auli.add(a);
			existrole=auli.get(0).getAuth();
			aulistr.add(existrole);
			
			String[] aul=aulistr.toArray(new String[aulistr.size()]);
			cususer=new customuserdetail(mems, AuthorityUtils.createAuthorityList(aul));
		}
		log.warn("loading service "+username);
		return cususer;
		
	}
}
