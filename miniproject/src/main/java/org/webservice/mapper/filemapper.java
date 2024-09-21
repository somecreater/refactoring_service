package org.webservice.mapper;

import java.util.List;

import org.webservice.domain.attachfile;
import org.webservice.domain.memberfile;

public interface filemapper {
	
	public void insertfile(attachfile file);
	public int deletefile(String uuid);
	public void deleteallfile(Long bno);
	public List<attachfile> getlistfile(Long bno);
	
	//db상의 파일과 실제 소유한 파일을 비교해서 db 정보를 업데이트 한다
	public List<attachfile> getoldfile();
	
	//유저별 파일 목록 관리
	public void insertmemfile(memberfile mfile);
	public int updatememfile(memberfile mfile);
	public int deletememfile(String pro_mem_file_code);
	public int deletememfilebybno(Long bno);
	public void deletememfileall(String userid);
	public memberfile getmemberfilebycode(String pro_mem_file_code);
	public List<memberfile> getmemberfilelist(String userid);
	public List<memberfile> getmemberfilebybno(Long bno);
	public void memdeletefile(memberfile mfile);
}
