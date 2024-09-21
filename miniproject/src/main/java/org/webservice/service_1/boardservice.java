package org.webservice.service_1;

import java.util.List;

import org.webservice.domain.attachfile;
import org.webservice.domain.auth;
import org.webservice.domain.auth_description;
import org.webservice.domain.banuser;
import org.webservice.domain.board;
import org.webservice.domain.boardlist;
import org.webservice.domain.boardsearch;
import org.webservice.domain.member;
import org.webservice.domain.member_info_etc;
import org.webservice.domain.memberfile;

public interface boardservice {
	
	
	//유저 차단 관련 서비스
	public boolean userban(String userid, String reason, int periods);
	public boolean userbanrelease(String userid);	
	public banuser getban(String uerid);
	
	//회원 관련 서비스
	public member getuser(String userid);
	public List<auth> getauth(String userid);
	public List<member> getmlist();
	public boolean exmemberinfo(String userid, String userpw);
	public String getuserid(String email, String phone);
	public void boardoutuser(String userid, String pass, boolean data);
	public boolean deletefile(List<memberfile> mfilelist);
	public boolean updateuserpass(String userid, String pass);
	public void member_insert(member m);
	
	//게시판 관련 서비스
	public void board_register(String boardname,String reguserid, String boardsub);
	public void boardlist_update(boardlist brd, String boardsubject);
	public boolean board_delete(String boardname);
	public void deleteboardbynameall(String boardname);
	public List<String> select_boardlist();
	public List<boardlist> select_boardlistset();
	public List<String> select_Boardaouthbyname(String boardname);
	public boardlist getboardlistbyname(String boardname);
	
	//권한 관련 서비스
	public boolean board_aouth(String boardname,String userid);
	public boolean board_aouth_insert(String userid, String auth);
	public boolean board_aouth_delete(String userid, String auth);
	public List<auth_description> auth_descrip();
	
	//게시판의 게시글 관련 서비스
	public board readBoard(Long bno);
	public void insertboard(board bd);
	public boolean deleteboard(Long bno);
	public boolean updateboard(board bd);
	public int getlisttotal(boardsearch search);
	public int getlisttotalbyid(String userid);
	public List<board> getList(boardsearch search);
	public List<board> getListbyid(String userid);
	public List<board> getListbyboardname(String boardname);
	public List<attachfile> getfilelist(Long bno);
	public void deletefilelist(Long bno);
	public void deletefileone(String uuid);
	
	//회원별 파일 관련 서비스
	/* 파일 임시 등록시 pro_member_file에 insert를 해야한다.(파일 컨트롤러 상의 uploadfile 메소드에 포함), 
	 * 임시 등록된 파일 삭제시 pro_member_file에서 해당 파일을 delete 해야한다.(파일 컨트롤러 상의 serverdeletefile 메소드에 포함),
	 * 마이 페이지에서 등록했던 파일을 pro_member_file에서 select 해서 보여줘야한다(보안 컨트롤러 상세서 myPage 메소드에 포함),
	 * 마이 페이지에서도 등록했던 파일을 삭제 가능하도록 함
	 */
	public void insertMemfile(memberfile memberfile);
	public boolean deleteMemfile(String pro_mem_file_code);
	public void deleteMemfileall(String userid);
	public memberfile getMemberfilebycode(String pro_mem_file_code);
	public List<memberfile> getMemberfilebyuserid(String userid);
	public List<memberfile> getMemberfilebybno(Long bno);
	
	//유저의 기타정보들을 관리하는 서비스
	public boolean updatememberetc(member_info_etc etc);
	public boolean deletememberetc(String userid);
	public boolean insertmemberetc(member_info_etc etc);
	public member_info_etc getmemberetc(String userid);
}
