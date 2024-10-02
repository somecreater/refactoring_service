package org.webservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    private String title;
    private String boardtype;
    private String writer;
    @Temporal(TemporalType.TIMESTAMP)
    private Date regdate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date udate;

    private long viscount;
    private int recommendation;


    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardtype", referencedColumnName = "boardname", insertable = false, updatable = false)
    private BoardTypeEntity boardTypeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer", referencedColumnName = "userid", insertable = false, updatable = false)
    private MemberEntity memberEntity;

    private int comment_num;
}
