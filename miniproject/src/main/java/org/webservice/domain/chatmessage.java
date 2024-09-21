package org.webservice.domain;

import java.util.Date;

import lombok.Data;

@Data
public class chatmessage {
	String roomcode;
	String userid;
	String content;
	String type; //"particate","exit","nomal","removechatroom"
	Date regdate;
}
