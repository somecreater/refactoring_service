package org.webservice.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;
@Data
public class boardlist {
	private Long boardnum;
	private String boardname;
	private String boardsubject;
	private Date regdate;
	private String reguserid;
	private List<String> manageridlist;
	
	public List<String> getManageridlist() {
        return this.manageridlist;
    }
}
