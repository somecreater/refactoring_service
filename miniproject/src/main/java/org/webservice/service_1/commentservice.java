package org.webservice.service_1;

import java.util.List;

import org.webservice.domain.boardsearch;
import org.webservice.domain.comment;
import org.webservice.domain.commentpage;

public interface commentservice {
	
	//댓글 관련 서비스
	public int registercomment(comment cmt);
	public comment getcomment(Long rno);
	public int updatecomment(comment cmt);
	public int deletecomment(Long rno);
	public int gettotalcommentcnt(Long bno);
	public int gettotalcommentcntbyid(String userid);
	
	//댓글 목록을 가져오고 페이지를 구현한 서비스
	public List<comment> getcmtlist(boardsearch search, Long bno);
	public List<comment> getcmtlistbyid(String userid);
	public commentpage getcmtlistpage(boardsearch search, Long bno);
}
