package org.webservice.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@RedisHash(value = "alarm_cache")
public class AlarmCacheEntity {
    //알람의 종류에 따라 구별(새 댓글, 친구 로그인 알림, 채팅 신청, 공지 사항....)={new comment, friend login, chat request, chat alert, announcement}
    //알림 자동 삭제... 하루에 한번씩, 설정에 따라서 일정한 주기로 삭제
    @Id
    private Long alarmid;
    private String userid;
    private String alarmtype;
    private String content;
    private boolean ischecked;

}
