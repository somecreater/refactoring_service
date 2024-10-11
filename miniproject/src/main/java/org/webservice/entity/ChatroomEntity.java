package org.webservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "chatroom")
public class ChatroomEntity {

    @Id
    private String chatroomcode; //"랜덤 UUID"_"채팅방제목"_"날짜"
    private String chatroomtitle;
    private String regid;
    @Temporal(TemporalType.TIMESTAMP)
    private Date regdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regid", referencedColumnName = "userid", insertable = false, updatable = false)
    private MemberEntity member;
}
