package com.cos.photogramstart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {
    private static final Logger log= LoggerFactory.getLogger(ImageController.class);

    @GetMapping({"/", "/image/story"})
    private String story(){
        log.info("** 로그인 실행됨");
        return "image/story";
    }
}
