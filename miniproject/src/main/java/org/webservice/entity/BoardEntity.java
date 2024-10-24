package org.webservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    private String title;

    private String bordcategory;
    private String writer;

    private String content;

    private LocalDateTime regdate;
    private LocalDateTime udate;

    private long viscount;
    private int recommendation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bordcategory", referencedColumnName = "boardname", insertable = false, updatable = false)
    private BoardTypeEntity boardTypeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer", referencedColumnName = "userid", insertable = false, updatable = false)
    private MemberEntity memberEntity;

    private int comment_num;
}
