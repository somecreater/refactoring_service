package org.webservice.task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.webservice.domain.banuser;
import org.webservice.mapper.boardmapper;
import org.webservice.service_1.boardservice;
import org.webservice.service_1.communicationservice;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class servercheck {
	@Autowired
	boardmapper bmapper;
	@Autowired
	communicationservice comunicatesesrvice;
	@Autowired
	boardservice bservice;
	//매일 확인 
	//벤 리스트 정보를 전부 가져와서 기간 지난것들을 전부 해제
	@Scheduled(fixedRate = 24 * 60 * 60 * 1000)
	public void checkban() {
		DateFormat datefmt=new SimpleDateFormat("yyyy-MM-dd");
		Date currentdate=new Date();
		List<banuser> banlist=bmapper.getbanlist();
		
		for(banuser user: banlist) {
			Date streddate=user.getEnddate();
			try {
				Date eddate=streddate;
				if(currentdate.after(eddate)) {
					bservice.userbanrelease(user.getUserid());
				}
			} catch (Exception e) {
				System.out.println("차단 체크 메소드가 비정상 실행되었습니다.");
			}
			
		}

		System.out.println("차단 체크 메소드가 정상 실행되었습니다.");
		log.info("정기 차단 해제");
	}
	
	//하루 지날때 마다 채팅방 리스트 자동 초기화
	@Scheduled(fixedRate = 24 * 60 * 60 * 1000)
	public void checkchatroom() {
		try {
			comunicatesesrvice.deleteallchatroom();
			System.out.println("채팅방 리스트가 초기화 되었습니다.");
		} catch (Exception e) {
			System.out.println("채팅방 리스트 초기화 도중 오류가 발생했습니다.");
		}
		
	}
	
	
}
