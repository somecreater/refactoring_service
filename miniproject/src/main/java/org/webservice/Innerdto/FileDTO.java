package org.webservice.Innerdto;

import lombok.Data;

@Data
public class FileDTO {
    String FileOrgName;
    String Path;
    long size;
    String FileType;
}
