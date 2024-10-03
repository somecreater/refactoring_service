package org.webservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "banlist")
public class BanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bannum;
    private String userid;
    private String banreason;
    private int period;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startdate;
    private Date enddate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", referencedColumnName = "userid", insertable = false, updatable = false)
    private MemberEntity member;
}
