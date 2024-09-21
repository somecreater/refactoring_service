package org.webservice.domain;

import java.util.Date;

import lombok.Data;
@Data
public class banuser {
	private Long bannum;
	private String userid;
	private String banreason;
	private int period;
	private Date startdate;
	private Date enddate;
	
}
