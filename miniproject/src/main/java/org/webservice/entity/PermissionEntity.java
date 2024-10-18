package org.webservice.entity;

import org.springframework.data.annotation.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@RedisHash(value = "permission")
public class PermissionEntity {
    //관리자용 허가 및 확인 알림
    //{게시판 개설 요청, 신고 사항, 문의 사항, 기타 사항}={new board request,report request, inquiry request}
    //이것은 관리자가 자체적으로 삭제하거나, 자동 삭제 설정할 때만 삭제
    @Id
    private Long alarmid;
    private String alarmtype;
    private String userid;
    private String content;

}
