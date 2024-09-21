package org.webservice.service_1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webservice.domain.boardsearch;
import org.webservice.domain.comment;
import org.webservice.domain.commentpage;
import org.webservice.mapper.boardmapper;
import org.webservice.mapper.commentmapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class commentserviceImpl implements commentservice{

	@Setter(onMethod_ = @Autowired)
	private commentmapper cmtmapper;
	
	@Setter(onMethod_ = @Autowired)
	private boardmapper brdmapper;
	
	@Override
	public int registercomment(comment cmt) {
		brdmapper.updatecmtcnt(cmt.getBno(), 1);
		return cmtmapper.insertcomment(cmt);
	}

	@Override
	public comment getcomment(Long rno) {
		
		return cmtmapper.readcomment(rno);
	}

	@Override
	public int updatecomment(comment cmt) {
		
		return cmtmapper.updatecomment(cmt);
	}

	@Override
	public int deletecomment(Long rno) {
		comment tmpcmt=cmtmapper.readcomment(rno);
		brdmapper.updatecmtcnt(tmpcmt.getBno(), -1);
		return cmtmapper.deletecomment(rno);
	}

	@Override
	public List<comment> getcmtlist(boardsearch search, Long bno) {
		
		return cmtmapper.getlistcomment(search, bno);
	}

	@Override
	public commentpage getcmtlistpage(boardsearch search, Long bno) {
		
		return new commentpage(cmtmapper.getcountcomment(bno),cmtmapper.getlistcomment(search, bno));
	}

	@Override
	public int gettotalcommentcnt(Long bno) {
		return cmtmapper.getcountcomment(bno);
	}

	@Override
	public int gettotalcommentcntbyid(String userid) {
		return cmtmapper.getcountcommentbyid(userid);
	}

	@Override
	public List<comment> getcmtlistbyid(String userid) {
		return cmtmapper.getlistcommentbyid(userid);
	}

}
