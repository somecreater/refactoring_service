package org.webservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "auth")
public class AuthEntity {
    @Id
    private String userid;
    private String auth;

    @OneToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", insertable = false, updatable = false)
    private MemberEntity member;
}
