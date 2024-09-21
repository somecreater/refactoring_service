package org.webservice.mapper;

import java.util.List;

import org.webservice.domain.auth;
import org.webservice.domain.member;
import org.webservice.domain.member_info_etc;

public interface membermapper {
	public member readmember(String userid);
	public String readmembername(String userid);
	public int insertmember(member member);
	public int updatemember(member member);
	public int deletemember(member member);
	public int insertauth(auth au);
	public int deleteauth(auth au);
	public void deleteauthbyid(String userid);
	public List<auth> readauth(String userid);
	public List<member> memberlist();
	public member checkmember(member mem);
	
	//유저들의 기타정보와 관련된 sql 문
	public int insertmemberetc(member_info_etc etc);
	public int deletememberetc(String userid);
	public int updatememberetc(member_info_etc etc);
	public member_info_etc readmemberetc(String userid);
	public String getmemberid(String mail);
	public String getmemberidbyphone(String phone);
}
