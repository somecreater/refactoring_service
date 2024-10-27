package org.webservice.Innerdto;

import lombok.Data;

@Data
public class AlarmDTO {

    //서버 내부용 알림 DTO
    private Long alarmid;
    private String userid;
    private String alarmtype;
    private String content;
    private boolean IsPriority;

}
