package org.webservice.refactoring_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webservice.Innerdto.AlarmDTO;

@Service
@Slf4j
public class AlertServiceImpl implements AlertService{
    @Override
    public AlarmDTO MakeAlarm(String AlarmType, String Content) {
        return null;
    }

    @Override
    public void SendAlarm(AlarmDTO alarmDTO) {

    }

    @Override
    public boolean DeleteAlarm(String AlarmID) {
        return false;
    }

    @Override
    public void BanAlarm(AlarmDTO alarmDTO) {

    }
}
