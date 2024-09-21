package org.webservice.domain;

import java.util.Date;

import lombok.Data;

@Data
public class member_info_etc {
	private Long member_etc;
	private String userid;
	private String mail;
	private String birth_date;
	private String about_me;
	private Date regdate;
	private Date udate;
}
