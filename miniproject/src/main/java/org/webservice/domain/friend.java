package org.webservice.domain;

import java.util.Date;

import lombok.Data;

@Data
public class friend {
	String friend_code;
	String userid;
	String fuserid;
	Date regdate;
}
