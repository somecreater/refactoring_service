package org.webservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "memberinfo")
public class MemberInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_etc;
    private String userid;
    private String mail;
    private String birth_date;
    private String about_me;

    @Temporal(TemporalType.TIMESTAMP)
    private Date regdate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date udate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", referencedColumnName = "userid", insertable = false, updatable = false)
    private MemberEntity member;
}
