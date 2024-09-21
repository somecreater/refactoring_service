package org.webservice.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;
@Data
public class member{
	private String userid;
	private String userpw;
	private String username;
	private String phone;
	private Date regdate;
	private Date udate;
	private boolean enabled;
	
	//유저의 권한 여부(일반 사용자, 갤러리 관리자(여러 갤러리의 관리자 일수도 있다),첫번째 행은 마스터 사용자)
	private List<auth> authlist;
}
