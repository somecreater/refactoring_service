package org.webservice.domain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
@Data
@AllArgsConstructor
@Getter
public class commentpage {
	private int replyCnt;
	private List<comment> list;
}
