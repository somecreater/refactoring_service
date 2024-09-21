package org.webservice.service_1;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.webservice.domain.boardsearch;
import org.webservice.domain.comment;
import org.webservice.mapper.boardmapper;
import org.webservice.mapper.commentmapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class commentcservicetest {

	@Setter(onMethod_= {@Autowired})
	public commentmapper cmapper;
	
	@Setter(onMethod_= {@Autowired})
	public boardmapper bmapper;
	
	//@Test
	@DisplayName("댓글 입력 테스트")
	public void testcommentinsert() {
		comment cmt=new comment();
		cmt.setBno(50L);
		cmt.setWriter("user00");
		cmt.setComments("이것은 테스트용 댓글입니다");
		cmapper.insertcomment(cmt);
		bmapper.updatecmtcnt(cmt.getBno(), 1);
		log.info(cmapper.getcountcomment(50L));
	}
	
	//@Test
	@DisplayName("댓글 삭제 테스트")
	public void testcommentdelete() {
		cmapper.deletecomment(1L);
	}

	//@Test
	@DisplayName("댓글 수정 테스트")
	public void testcommentupdate() {
		comment cmt=cmapper.readcomment(3L);
		cmt.setComments("수정된 댓글 입니다");
		log.info(cmapper.updatecomment(cmt));
	}
	
	//@Test
	@DisplayName("댓글 리스트 조회 테스트")
	public void testcommentlist() {
		List<comment> cmtlist=new ArrayList<comment>();
		boardsearch brdsrch=new boardsearch();
		brdsrch.setPageNum(1);
		cmtlist=cmapper.getlistcomment(brdsrch, 50L);
		int number=cmapper.getcountcomment(50L);
		
		log.info("게시물 50번의 댓글 총개수: "+number);
	}
	
	
}
