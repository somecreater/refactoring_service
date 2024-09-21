package org.webservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.webservice.domain.auth;
import org.webservice.domain.auth_description;
import org.webservice.domain.banuser;
import org.webservice.domain.board;
import org.webservice.domain.boardlist;
import org.webservice.domain.boardpage;
import org.webservice.domain.boardsearch;
import org.webservice.domain.comment;
import org.webservice.domain.friend;
import org.webservice.domain.member;
import org.webservice.domain.member_info_etc;
import org.webservice.domain.memberfile;
import org.webservice.mapper.filemapper;
import org.webservice.mapper.membermapper;
import org.webservice.service_1.boardservice;
import org.webservice.service_1.commentservice;
import org.webservice.service_1.communicationservice;
import org.webservice.service_1.etcservice;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class securitycontroller {

	//@Autowired
	//private customUserDetailService csuser;
	@Setter(onMethod_ = @Autowired)
	private membermapper mmapper;
	@Setter(onMethod_ = @Autowired)
	private filemapper fmapper;
	@Setter(onMethod_ = @Autowired)
	private boardservice bservice;
	@Setter(onMethod_ = @Autowired)
	private commentservice cmtservice;
	@Setter(onMethod_ = @Autowired)
	private etcservice eservice;
	@Setter(onMethod_=@Autowired)
	private PasswordEncoder pencoder;
	@Setter(onMethod_=@Autowired)
	private communicationservice communicateservice;
	
	//비밀번호 정규식
	private static final String pass_regex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
	//휴대폰 정규식
	private static final String phone_regex = "^\\d{10,11}$";

//로그인 화면
@GetMapping("/loginboard")
public String loginview() {
	return "loginboard";
}

//로그인 실행
@PostMapping("/loginaction")
public String loginaction() {
	return "redirect:/board/listboard";
}

//유저의 정보를 가져온다
@GetMapping("/getuserinfo")
@ResponseBody
public Map<String, Object> getuserinfo(String userid){
	//log.info(userid);
	member minfo=mmapper.readmember(userid);
	Map<String,Object> result=new HashMap<String, Object>();
	result.put("userinformation", minfo);
	return result;
}


//유저의 아이디를 받아서 유저의 이름을 가져온다
@GetMapping("/getuserinfoname")
@ResponseBody
public Map<String, Object> getuserinfoname(String userid){
	//log.info(userid);
	Map<String,Object> result=new HashMap<String, Object>();
	if(userid!=null) {
		member minfo=mmapper.readmember(userid);
		String realname=minfo.getUsername();
	
		result.put("userrealname", realname);
	}else {
		result.put("userrealname","존재하지 않는 아이디입니다.");
	}
	return result;
}

//로그인 오류화면
@GetMapping("/loginerror")
public void loginerror() {
	
}

//아이디 차단여부를 확인한다.
@GetMapping("/checkban")
@ResponseBody
public Map<String,Object> logindeny(String userid, String userpass) {
	Map<String, Object> response=new HashMap<String, Object>();
	if(bservice.exmemberinfo(userid, userpass)) {
		banuser buser=bservice.getban(userid);
		response.put("bancontent", buser);
		response.put("result", "success");
	}else {
		response.put("result", "failure");
	}
	
	
	return response;
}

//로그아웃을 수행한다.
@PostMapping("/logoutaction")
public String logoutaction() {
	return "redirect:/board/listboard";
}

//마이페이지 화면
@PreAuthorize("authenticated()")
@GetMapping("/myPage")
public void myPage(Model model) {
	//마이페이지,작성 글과 댓글 기록, 업로드 파일 기록, 회원정보, 친구 목록을 가져온다.
	Authentication auth=SecurityContextHolder.getContext().getAuthentication();
	String userid=auth.getName();
	member minfo=mmapper.readmember(userid);
	List<board> mbrdlist=bservice.getListbyid(userid);
	List<comment> mcmtlist=cmtservice.getcmtlistbyid(userid);
	List<memberfile> mfilelistList=bservice.getMemberfilebyuserid(userid);
	member_info_etc minfoetc=bservice.getmemberetc(userid);
	List<friend> friendlist=communicateservice.getlistfriend(userid);
	model.addAttribute("myid", userid);
	model.addAttribute("memberinfoetc", minfoetc);
	model.addAttribute("memberinfo", minfo);
	model.addAttribute("boardrecordsize", mbrdlist.size());
	model.addAttribute("boardrecord", mbrdlist);
	model.addAttribute("commentrecordsize", mcmtlist.size());
	model.addAttribute("commentrecord", mcmtlist);
	model.addAttribute("filerecordsize", mfilelistList.size());
	model.addAttribute("filerecord", mfilelistList);
	model.addAttribute("friendlist", friendlist);
	model.addAttribute("friendlistsize", friendlist.size());
}

//회원의 기타정보를 가져온다.
@PreAuthorize("authenticated()")
@GetMapping("/myPageetc")
@ResponseBody
public Map<String,Object> myPageetc(String userid){
	Map<String, Object> response=new HashMap<String, Object>();
	member_info_etc minfo_etc=bservice.getmemberetc(userid);
	response.put("etc", minfo_etc);
	return response;
}

//회원의 기타정보를 입력한다.
@PreAuthorize("authenticated()")
@PostMapping("/etcinsert")
public String etcinsert(member_info_etc infoetc, RedirectAttributes rttr){
	String userid=infoetc.getUserid();
	Authentication auth=SecurityContextHolder.getContext().getAuthentication();
	String exuserid=auth.getName();
	if(exuserid.compareTo(userid)!=0) {
		rttr.addAttribute("result", "violate");
		return "redirect:/myPage";
	}
	else if(bservice.insertmemberetc(infoetc)) {
		rttr.addAttribute("result", "success");
		return "redirect:/myPage";
	}else {
		rttr.addAttribute("result", "failure");
		return "redirect:/myPage";
	}
}

//회원의 기타정보를 업데이트한다
@PreAuthorize("authenticated()")
@PostMapping("/etcupdate")
@ResponseBody
public Map<String,Object> etcupdate(String userid, String birthday, String mail, String aboutme){
	Map<String, Object> response=new HashMap<String, Object>();

	Authentication auth=SecurityContextHolder.getContext().getAuthentication();
	String exuserid=auth.getName();
	
	member_info_etc infoetc=bservice.getmemberetc(userid);
	infoetc.setBirth_date(birthday);
	infoetc.setMail(mail);
	infoetc.setAbout_me(aboutme);
	
	if(exuserid.compareTo(userid)!=0) {
		response.put("result","violate");
	}
	else if(bservice.updatememberetc(infoetc)) {
		response.put("result", "success");
	}
	else {
		response.put("result", "failure");
	}
	return response;
}

//회원의 기타정보를 삭제한다.
@PreAuthorize("authenticated()")
@PostMapping("/etcdelete")
@ResponseBody
public Map<String,Object> etcdelete(String userid){
	Map<String, Object> response=new HashMap<String, Object>();
	Authentication auth=SecurityContextHolder.getContext().getAuthentication();
	String exuserid=auth.getName();
	
	if(exuserid.compareTo(userid)!=0) {
		response.put("result","violate");
	}
	else if(bservice.deletememberetc(userid)) {
		response.put("result", "success");
	}
	else {
		response.put("result", "failure");
	}
	return response;
}

//회원의 기타정보를 가져온다.
@PreAuthorize("authenticated()")
@GetMapping("/etcread")
@ResponseBody
public Map<String,Object> etcread(String userid){
	Map<String, Object> response=new HashMap<String, Object>();
	member_info_etc etc=bservice.getmemberetc(userid);
	response.put("etc_info", etc);
	
	return response;
}

//회원가입 화면
@GetMapping("/boardjoin")
public void boardjoin() {
	
}

//받은 정보로 회원가입을 수행한다.
@PostMapping("/boardjoinaction")
public String boardjoinaction(String id, String passwd, String username, String phone) {
	
	auth au=new auth();
	String userid=id;
	String userpw=passwd;
	String usname=username;
	String pne=phone;
	String enpw=pencoder.encode(passwd);
	if(mmapper.readmember(userid)!=null) {
		return  "redirect:/loginboard";
	}
	member joinmem=new member();
	
	au.setUserid(userid);
	au.setAuth("common");
	
	joinmem.setUserid(userid);
	joinmem.setUserpw(enpw);
	joinmem.setUsername(username);
	joinmem.setPhone(phone);
	
	bservice.member_insert(joinmem);
	mmapper.insertauth(au);
	
	return "redirect:/loginboard";
}

//아이디가 존재하는지 체크한다
@GetMapping("/idcheckaction")
@ResponseBody
public Map<String, Object> idcheckaction(String id){
	Map<String, Object> response=new HashMap<String, Object>();
	
	if(mmapper.readmember(id)!=null) {
		response.put("result", "failure");
	}else {
		response.put("result", "success");
	}
	
	return response;
}

//비밀번호, 닉네임, 휴대폰번호 입력값들이 입력 조건에 맞는지 체크한다
@GetMapping("/etcdatacheckaction")
@ResponseBody
public Map<String, Object> etcdatacheckaction(String passwd, String username, String phonenumber)
{
	Map<String, Object> response=new HashMap<String, Object>();
	Pattern pwptn=Pattern.compile(pass_regex);
	Pattern phptn=Pattern.compile(phone_regex);
	
	Matcher pwmach=pwptn.matcher(passwd);
	Matcher phmach=phptn.matcher(phonenumber);
	
	if(!pwmach.matches()||!phmach.matches()||username.length()<3) {
		response.put("result", "failure");
	}
	else {
		response.put("result", "success");
	}
	return response;
}

//회원 탈퇴 화면
@GetMapping("/boardout")
public void boardout() {
	
}

//회원 탈퇴를 수행한다.
@PreAuthorize("authenticated()")
@PostMapping("/boardoutaction")
@ResponseBody
public Map<String, Object> boardoutaction(String id, String pass, boolean datareset) {

	Map<String, Object> response=new HashMap<String, Object>();
	Authentication auth=SecurityContextHolder.getContext().getAuthentication();
	String exuserid=auth.getName();
	try {
		if(exuserid.compareTo(id)==0) {
			bservice.boardoutuser(id, pass, datareset);
		}
		response.put("result", "success");
	}catch (Exception e) {
		response.put("result","failure");
	}
	return response;
}

///아이디, 비밀번호를 찾는 화면
@GetMapping("/idsearch")
public void idsearch() {
	
}

//권한 및 회원 차단 여부 관리 화면
@PreAuthorize("hasAuthority('master')")
@GetMapping("/authmanage")
public void authmanage(Model model) {
	List<member> mlist=bservice.getmlist();
	List<auth_description> audeslist=bservice.auth_descrip();
	List<boardlist> brdlist=bservice.select_boardlistset();
	for(int i=0;i<mlist.size();i++) {
		mlist.get(i).setAuthlist(bservice.getauth(mlist.get(i).getUserid()));
		mlist.get(i).setUserpw(null);
	}
	model.addAttribute("brdlist", brdlist);
	model.addAttribute("authdescrip", audeslist);
	model.addAttribute("userlist", mlist);
}

//특정 권한을 특정 회원에게 부여한다.
@PreAuthorize("hasAuthority('master')")
@PostMapping("/authaction")
@ResponseBody
public Map<String,Object> authaction(String userid, String auth){
	Map<String, Object> response=new HashMap<String, Object>();
	if(bservice.board_aouth_insert(userid, auth)) {
		response.put("result", "success");
	}else {
		response.put("result", "failure");
	}
	return response;
}

//특정 회원로부터 특정 권한을 삭제한다.
@PreAuthorize("hasAuthority('master')")
@PostMapping("/authdeaction")
@ResponseBody
public Map<String,Object> authdeaction(String userid, String auth){
	Map<String, Object> response=new HashMap<String, Object>();
	if(bservice.board_aouth_delete(userid, auth)) {
		response.put("result", "success");
	}else {
		response.put("result", "failure");
	}
	return response;
}

//특정 회원의 차단정보를 가져온다.
@PreAuthorize("hasAuthority('master')")
@GetMapping("/getban")
@ResponseBody
public Map<String,Object> getban(String userid){
	Map<String, Object> response=new HashMap<String, Object>();
	banuser buser=bservice.getban(userid);
	response.put("banuserobj", buser);
	return response;
}

//특정 회원의 차단을 해제한다.
@PreAuthorize("hasAuthority('master')")
@PostMapping("/bandeaction")
@ResponseBody
public Map<String,Object> bandeaction(String userid){
	Map<String, Object> response=new HashMap<String, Object>();
	if(bservice.userbanrelease(userid)) {
		response.put("result", "success");
	}else {
		response.put("result", "failure");
	}
	
	return response;
}

//특정 회원을 차단한다.
@PreAuthorize("hasAuthority('master')")
@PostMapping("/banaction")
@ResponseBody
public Map<String,Object> banaction(String userid, String banreason, int bantime){
	//차단을 초기화하고 새로 집어 넣는다.
	Map<String, Object> response=new HashMap<String, Object>();
	if(bservice.userbanrelease(userid)) {
		if(bservice.userban(userid, banreason, bantime)) {
			response.put("result", "success");
		}else {
			response.put("result", "failure");
		}
	}else {
		response.put("result", "failure");
	}
	
	return response;
}


//아이디를 찾는 메소드
@GetMapping("/searchauth")
@ResponseBody
public Map<String, Object> searchauth(String email, String phone){
	Map<String, Object> response=new HashMap<String, Object>();
	int validnum=eservice.createcertification();
	try {
		eservice.createjms(email, validnum);
		response.put("result", "success");
	}catch (Exception e) {
		
		//현재 위의 기능이 제대로 동작을 안하는 상태이다(2024/5/21)
		//만약 메일 인증이 제대로 동작을 안하면 db내 저장된 정보를 이용해서 아이디 확인
		String subid=bservice.getuserid(email, phone);
		if(subid!=null) {
			response.put("userid",subid);
		}else {
			response.put("userid", "noid");
		}

		response.put("result", "subsuccess");
	}
	return response;
}
//비밀번호를 찾는 메소드
@GetMapping("/searchpass")
@ResponseBody
public Map<String,Object> searchpass(String userid, String email, String phone){
	Map<String, Object> response=new HashMap<String, Object>();
	
	String subid=bservice.getuserid(email, phone);
	if(subid.compareTo(userid)==0) {
		response.put("result", "success");
	}else {
		response.put("result", "failure");
	}
	
	return response;
}


//비밀번호 재설정 메소드
@PostMapping("/resetpassword")
@ResponseBody
public Map<String,Object> resetpassword(String userid, String newpass, String renewpass){
	Map<String, Object> response=new HashMap<String, Object>();
	Pattern pwptn=Pattern.compile(pass_regex);
	Matcher mch=pwptn.matcher(newpass);
	
	if(!mch.matches()) {
		response.put("result", "notpass");
	}else if(newpass.compareTo(renewpass)!=0) {
		response.put("result", "notmatch");
	}else {
		if(bservice.updateuserpass(userid, newpass)) {
			response.put("result", "success");
		}else {
			response.put("result", "failure");
		}
	}
	return response;
}

//사용안하는 api
//숫자 입력을 이용해서 인증을 수행한다.
@PostMapping("/varifyauth")
@ResponseBody
public Map<String, Object> varifyauth(int number){
	Map<String, Object> response=new HashMap<String, Object>();
	if(eservice.varify(number)) {
		response.put("result", "success");
	}else {
		response.put("result", "failure");
	}
	return response;
}
}
