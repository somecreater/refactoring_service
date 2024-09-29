package org.webservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "member")
public class MemberEntity {

    @Id
    private String userid;
    private String userpw;
    private String username;
    private String phone;

    @CreationTimestamp
    private Date regdate;

    private Date udate;
    private boolean enabled;

    @OneToOne(mappedBy = "member")
    private AuthEntity auth;
}
