package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ImageController {
    private static final Logger log= LoggerFactory.getLogger(ImageController.class);
    private final ImageService imageService;

    @GetMapping({"/","/image/story"})
    private String story(){
        log.info("** 로그인 실행됨");
        return "image/story";
    }

    @GetMapping({"/image/popular"})
    private String popular(){
        return "image/popular";
    }

    @GetMapping({"/image/upload"})
    private String upload() {
        return "image/upload";
    }

    @PostMapping({"/image"})
    private String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(imageUploadDto.getFile().isEmpty()){
            throw new CustomValidationException("이미지가 첨부되지 않았습니다",null);
        }
        imageService.사진업로드(imageUploadDto, principalDetails);
        //서비스호출
        return "redirect:/user/"+principalDetails.getUser().getId();
    }



   }
