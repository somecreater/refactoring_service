package org.webservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.webservice.domain.boardsearch;
import org.webservice.domain.comment;

public interface commentmapper {
	public comment readcomment(Long rno);
	public int insertcomment(comment cmt);
	public int deletecomment(Long rno);
	public int deletecommentbybno(Long bno);
	public int deletecommentbyid(String writer);
	public int updatecomment(comment cmt);
	
	public List<comment> getlistcomment(@Param("search")boardsearch search,@Param("bno") Long bno);
	public List<comment> getlistcommentbyid(String writer);
	public int getcountcommentbyid(String writer);
	public int getcountcomment(Long bno);
}
