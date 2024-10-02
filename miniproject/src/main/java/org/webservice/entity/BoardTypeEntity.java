package org.webservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "boardtype")
public class BoardTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardnum;
    private String boardname;
    private String boardsubject;
    private String creater;

    @Temporal(TemporalType.TIMESTAMP)
    private Date regdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creater", referencedColumnName = "userid", insertable = false, updatable = false)
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "boardTypeEntity", fetch = FetchType.LAZY)
    private List<BoardEntity> boardEntities;
}
