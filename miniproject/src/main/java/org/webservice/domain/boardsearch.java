package org.webservice.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
public class boardsearch {
	private int pageNum;
	private int amount;

	private String boardname;
	private String type;
	private String keyword;
	
	//처음 접속시 실행
	public boardsearch() {
		this.pageNum=1;
		this.amount=10;
	}
	
	public boardsearch(int pagenum,int amount) {
		this.pageNum=pagenum;
		this.amount=amount;
	}
	
	public boardsearch(String boardname,int pagenum,int amount) {
		this.boardname=boardname;
		this.pageNum=pagenum;
		this.amount=amount;
	}
	
	//이동시 실행
	public boardsearch(int pagenum, int amount, String type, String keyword) {
		this.pageNum=pagenum;
		this.amount=amount;
		this.type=type;
		this.keyword=keyword;
	}
	public String[] getTypeArr() {

		return type == null ? new String[] {} : type.split("");
	}
	
	public String getListLink() {

		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount())
				.queryParam("type", this.getType())
				.queryParam("keyword", this.getKeyword());

		return builder.toUriString();

	}
}
