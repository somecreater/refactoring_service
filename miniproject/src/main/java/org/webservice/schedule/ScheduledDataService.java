package org.webservice.schedule;

public interface ScheduledDataService {

    //하루에 한 번씩 서버 내 파일과 db상의 파일 정보 비교하여 동기화
    public boolean FileSynchronization();
    //하루에 한 번씩 Redis 내 남은 데이터 삭제
    public boolean CacheDelete();

}
