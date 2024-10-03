package org.webservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    private Long bno;
    private String comments;
    private String writer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date regdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer", referencedColumnName = "userid", insertable = false, updatable = false)
    private MemberEntity member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bno", referencedColumnName = "bno", insertable = false, updatable = false)
    private BoardEntity board;
}
