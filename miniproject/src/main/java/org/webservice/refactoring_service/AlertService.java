package org.webservice.refactoring_service;

import org.webservice.Innerdto.AlarmDTO;

public interface AlertService {

    public AlarmDTO MakeAlarm(String AlarmType, String Content);
    public void SendAlarm(AlarmDTO alarmDTO);
    public boolean DeleteAlarm(String AlarmID);

    //차단 알림
    public void BanAlarm(AlarmDTO alarmDTO);
}
