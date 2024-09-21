package org.webservice.service_1;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.webservice.domain.auth;
import org.webservice.domain.member;
import org.webservice.mapper.membermapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class membermappertest {
	
	@Setter(onMethod_=@Autowired)
	private PasswordEncoder pwencoder;
	@Setter(onMethod_=@Autowired)
	private membermapper mapper;
	
	//@Test
	public void testread() {
		member m=mapper.readmember("user11");
		log.info(m.getUserpw());
		
	}
	
	//@Test
	public void testinsert() {
		for(int i=0;i<100;i++) {
			List<auth> aulist=new ArrayList<auth>();
			auth a=new auth();
			String mid="user"+i;
			String mpw=pwencoder.encode("pw"+i);
			String mname="일반 사용자"+i;
			member m=new member();
			a.setUserid(mid);
			a.setAuth("common");
			aulist.add(a);
			
			m.setUserid(mid);
			m.setUserpw(mpw);
			m.setUsername(mname);
			m.setPhone("1111-1111-1111");
			m.setEnabled(true);
			m.setAuthlist(aulist);
			mapper.insertmember(m);
		}
		
	}
	
	@Test
	public void testinsertauth() {
		for(int i=0;i<100;i++) {
			auth a=new auth();
			String mid="user"+i;
			String au="common";
			
			a.setUserid(mid);
			a.setAuth(au);
			mapper.insertauth(a);
		}
	}
	
}
