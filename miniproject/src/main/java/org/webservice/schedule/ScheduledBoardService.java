package org.webservice.schedule;

public interface ScheduledBoardService {

    //조회수 업데이트, 추천수 업데이트(일정한 주기로 특정 범위에서만 실행, 실시간성이 떨어져도 안정성 보장)
    public boolean UpdateViscount(Long bno);
    public boolean UpdateRecommendation(Long bno);

    //

}
