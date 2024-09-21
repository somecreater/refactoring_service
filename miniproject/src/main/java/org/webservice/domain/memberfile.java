package org.webservice.domain;

import java.util.Date;

import lombok.Data;

@Data
public class memberfile {
	private String pro_mem_file_code;
	private String userid;
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean image;
	private Long bno;
	private Date regDate;
}
