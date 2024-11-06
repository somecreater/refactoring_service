package org.webservice.refactoring_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.webservice.refactoring_service.FileService;

@Controller
public class FileController {

    @Autowired
    public FileService fileService;

}
