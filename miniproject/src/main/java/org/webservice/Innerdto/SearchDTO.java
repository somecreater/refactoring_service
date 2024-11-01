package org.webservice.Innerdto;

import lombok.Data;

@Data
public class SearchDTO {
    //board,comment,chatroom
    private String DataType;
    //{title,content,boardtype,userid},{title,userid},{title,content,userid}
    private String SearchType;
    //검색 내용
    private String Content;
}
