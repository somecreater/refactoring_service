package org.webservice.entity;

import org.springframework.data.annotation.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@RedisHash(value = "alarm_cache")
public class AlarmCacheEntity {
    /*
    알람의 종류에 따라 구별(새 댓글, 친구 로그인 알림, 채팅 신청, 공지 사항....)={new comment, friend login, chat request, chat alert, announcement}
    알림 자동 삭제... 하루에 한번씩, 설정에 따라서 일정한 주기로 삭제
    관리자용 알림(IsPriority가 true인 알림)은 자체적인 삭제나, 자동 삭제 설정시에만 자동 삭제

    일반 유저 알림
    (새로운 댓글, 친구 신청 및 삭제 알림, 친구 로그인 알림,
    채팅방 초대, 채팅 알림, 메일 알림,
    권한 획득 알림, 게시판 개설 허가 알림, 공지사항)

    관리자 유저 알림
    (일반 유저 알림 포함,
    공지 사항 수정 알림, 권한 수정 알림, 문의 알림,
    신규 가입 알림(따로 분리해서 보관))
    */
    @Id
    private Long alarmid;
    private String userid;
    private String alarmtype;
    private String content;
    private boolean IsPriority;
    private boolean ischecked;

}
