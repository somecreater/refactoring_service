package org.webservice.refactoring_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.webservice.refactoring_service.BoardService;
import org.webservice.refactoring_service.FileService;

@Controller
public class BoardController {

    @Autowired
    public BoardService boardService;
    @Autowired
    public FileService fileService;


}
