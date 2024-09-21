package org.webservice.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.webservice.domain.attachfile;
import org.webservice.domain.file;
import org.webservice.domain.memberfile;
import org.webservice.mapper.filemapper;
import org.webservice.mapper.membermapper;
import org.webservice.service_1.boardservice;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class filecontroller {

	@Autowired
	private boardservice bservice;
	
	//파일을 서버에 업로드한다.
	@PreAuthorize("authenticated()")
	@PostMapping(value="/uploadFile",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String,Object> uploadfile(MultipartFile[] uploadFile) {
		
		Map<String, Object> response=new HashMap<String, Object>();
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String userid=auth.getName();
		
		List<file> filelist=new ArrayList<file>();
		List<File> thumbnaillist=new ArrayList<>();
		String result="upload_fail";
		String topuploadfolder="D:\\server\\temp";
		
		Date datevalue=new Date();
		String uploaddate=new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(datevalue);
		String uploadfolder=uploaddate.replace("-",File.separator);
		File upload=new File(topuploadfolder,uploadfolder);
		
		if(upload.exists()==false)
		{
			upload.mkdirs();
		}
		
		for(MultipartFile multipartFile:uploadFile) {
			log.info("this is for loop");
			
			file achfile=new file();
			memberfile memfile=new memberfile();
			String pro_mfile_code;
			memfile.setUserid(userid);
			
			String fileorgname=multipartFile.getOriginalFilename();
			fileorgname=fileorgname.substring(fileorgname.lastIndexOf("//")+1);
			achfile.setFileName(fileorgname);
			memfile.setFileName(fileorgname);
			
			UUID uuid=UUID.randomUUID();
			String filerename=uuid+"_"+fileorgname;
			achfile.setUuid(uuid.toString());
			memfile.setUuid(uuid.toString());
			
			File saveFile=new File(upload,filerename);
			log.info(saveFile.getAbsolutePath());
			achfile.setUploadPath(uploadfolder);
			memfile.setUploadPath(uploadfolder);
			try {
				if(!checkFile(saveFile)) {
					log.info("this file is reject");
					filelist=null;
					thumbnaillist=null;
					break;
				}
				result="upload_success";
				log.info("test"+saveFile.getAbsolutePath());
				multipartFile.transferTo(saveFile);
				
				if(checkImg(saveFile)) 
				{
					achfile.setImage(true);
					memfile.setImage(true);
					
					pro_mfile_code=userid+"_"+uploadfolder+"_"+uuid.toString()+"_"+fileorgname+"_"+"1";
					
					File thimgfile=new File(upload,"th_"+filerename);
					FileOutputStream thumboutfile=new FileOutputStream(thimgfile);
					Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumboutfile, 100, 100);
					
					log.info("thumnailfile info: "+thimgfile.getName()+",  "+thimgfile.getAbsolutePath());
					thumbnaillist.add(thimgfile);
					thumboutfile.close();
				}
				else 
				{
					memfile.setImage(false);
					pro_mfile_code=userid+"_"+uploadfolder+"_"+uuid.toString()+"_"+fileorgname+"_"+"0";
					
					achfile.setImage(false);
				}
				memfile.setPro_mem_file_code(pro_mfile_code);
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			log.info("file class info: "+achfile.getFileName()+",  "+achfile.getUploadPath()+",  "+achfile.getUuid());
			bservice.insertMemfile(memfile);
			filelist.add(achfile);
			
		}
		log.info("for loop end");
		response.put("attachfilelist", filelist);
		response.put("thumbnaillist", thumbnaillist);
		response.put("result",result);
		
		return response;
	}
	
	//파일의 형태를 검사한다.
	public boolean checkFile(File achfile) {
		String filefullname = achfile.getName();
		int filesize=(int)achfile.length();
		boolean checksecurity=true;
		int lastposition = filefullname.lastIndexOf(".");
		String fileextend = filefullname.substring(lastposition + 1);
		String[] dangerextend= {"exe","js","zip","sh","alz","Ink"};
		//파일을 좀더 자세히 검사하는 코드를 추가하면 좋다.
		
		
		for(String extend:dangerextend) {
			if(fileextend.contains(extend)) {
				checksecurity=false;
			}else if(filesize>524288000){
				checksecurity=false;
			}
		}
		
		return checksecurity;
	}
	
	//파일이 이미지인지 검사한다.
	public boolean checkImg(File achfile) {
		String filefullname = achfile.getName();
		boolean isImg = false;
		int lastposition = filefullname.lastIndexOf(".");
		String fileextend = filefullname.substring(lastposition + 1);
		String[] imgextend = { "jpg", "jpeg", "png", "gif", "bmp" };

		for (String extend : imgextend) {
			if (fileextend.contains(extend)) {
				isImg = true;
				break;
			}
		}
		return isImg;
	}
	
	
	//이미지 클릭시 원 이미지로 보여준다.
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<Resource> display(String fileuri) {
		//결국 반환값은 이미지가 되어야 한다.
		String topuri="D:\\server\\temp";
		File file=new File(topuri,fileuri);
		
		Resource resource=new FileSystemResource(file);
				
		HttpHeaders header=new HttpHeaders();
		Path filePath=null;
		filePath=Paths.get(file.getAbsolutePath());
		
		try {
			header.add("Content-Type", Files.probeContentType(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource,header,HttpStatus.OK);
	}
	
	//파일 다운로드를 수행한다.
	@GetMapping(value="/download",produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> download(String fileuri) throws IOException {
		String orifileuri=URLDecoder.decode(fileuri, "UTF-8");
		Resource resource=new FileSystemResource("D:\\server\\temp\\"+orifileuri);
		
		log.info(resource.getURI());
		
		String orignalfilename=resource.getFilename();
		
		String realfilename=orignalfilename.substring(orignalfilename.indexOf("_") + 1);
		HttpHeaders header=new HttpHeaders();
		try {
			header.add("Content-Disposition", "attachment; filename="+new String(realfilename.getBytes("UTF-8"),"ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource,header,HttpStatus.OK);
	}
	
	//파일 삭제를 수행한다.
	//입력값으로 resultbody에 올라와있는 값을 이용해서 생성한 filelist를 받아서 해당경로의 파일들을 전부 삭제
	@PreAuthorize("authenticated()")
	@PostMapping("/deletefile")
	@ResponseBody
	public Map<String,Object> serverdeletefile(String fileuri, boolean filetype){
		Map<String, Object> response=new HashMap<String, Object>();
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String userid=auth.getName();
		String pro_mfile_code="";
		
		String topuri="D:\\server\\temp";
		try {
			String detailuri=URLDecoder.decode(fileuri, "UTF-8");
			File file=new File(topuri, detailuri);
			
			file.delete();
			String orgfileuri=file.getAbsolutePath();
			String orgfilename=orgfileuri.substring(orgfileuri.lastIndexOf("\\") + 1);

			
			String onlyuri=detailuri.substring(0,detailuri.lastIndexOf("/"));
			String orguuid=orgfilename.substring(0, orgfilename.indexOf("_")-1);
			log.info(orgfilename);
			if(filetype) {
			pro_mfile_code=userid+"_"+onlyuri+"_"+orgfilename+"_"+"1";
			}else {
				pro_mfile_code=userid+"_"+onlyuri+"_"+orgfilename+"_"+"0";
			}
			
			log.info(pro_mfile_code);
			if(bservice.getMemberfilebycode(pro_mfile_code).getBno()!=null) {
				bservice.deletefileone(bservice.getMemberfilebycode(pro_mfile_code).getUuid());
			}
			if(bservice.deleteMemfile(pro_mfile_code)) {
				log.info("파일 기록도 삭제됨");
			}
			else {
				log.info("파일 기록 삭제 실패");
			}
			
			if(filetype) {
				String thfilename="th_"+orgfilename;
				String thfileuri=orgfileuri.replace(orgfilename,thfilename);		
				File thfile=new File(thfileuri);
				thfile.delete();
				
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		response.put("result", "success");
		return response;
	}
	
}
