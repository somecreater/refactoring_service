package org.webservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.webservice.domain.attachfile;
import org.webservice.domain.auth;
import org.webservice.domain.auth_description;
import org.webservice.domain.banuser;
import org.webservice.domain.board;
import org.webservice.domain.boardlist;
import org.webservice.domain.boardsearch;

public interface boardmapper {
	public int banusers(banuser user);
	public boolean banusersadd(String userid);
	public int banuserrealease(String userid);
	public boolean bansuerrealeaseadd(String userid);
	public int banselect(String userid);
	public List<banuser> getbanlist();
	public banuser getbanuser(String userid);
	
	public List<auth_description> getauthdescription();
	public List<auth> getauthbyid(String userid);
	public List<boardlist> selectboardlistset();
	public List<String> selectboardlist();
	public boardlist getboardlistbynum(Long boardnum);
	public boardlist getboardlistbyname(String boardname);
	public void createboard(boardlist brdlist);
	public void updateboardlist(boardlist brdlist);
	public int deleteaouthboard(auth ath);
	public int aouthboard(auth ath);
	public String select_boardaouth(String boardname);
	public List<String> select_boardaouthbyname(String auth);
	public int board_delete(String boardname);
	
	public board readboard(Long bno);
	public int deleteboard(Long bno);
	public int deleteboardbyname(String boardname);
	public int deleteboardbyid(String userid);
	public int updateboard(board bd);
	public void insertboard(board bd);
	public List<board> getlistboard();
	public List<board> getlistsearchboard(boardsearch search);
	public List<board> getlistboardbyid(String userid);
	public List<board> getlistboardbybrdname(String boardname);
	public int getcntbyid(String userid);
	
	public void updatecmtcnt(@Param("bno") Long bno, @Param("amount") int amount);
	public int gettotalcntboard(boardsearch search);
	public List<attachfile> findfilebybno(Long bno);
}
