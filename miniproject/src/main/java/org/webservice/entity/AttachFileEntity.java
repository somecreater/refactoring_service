package org.webservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "attachfile")
public class AttachFileEntity {

    @Id
    private String fileid;
    private String filename;
    private String uploadpath;
    private String uuid;
    private String type;
    private Long bno;

    @OneToOne
    @JoinColumn(name = "bno",referencedColumnName = "bno", insertable = false, updatable = false)
    private BoardEntity boardEntity;
}
