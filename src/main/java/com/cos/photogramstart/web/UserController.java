package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {


    @GetMapping("/user/{id}")
    public String profile(@PathVariable int id){
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id,
                         @AuthenticationPrincipal PrincipalDetails principalDetails
                         ){
//        System.out.println("세션정보"+principalDetails.getUser());
//        Authentication auth =SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("직접찾은 세션정보"+auth.getPrincipal());

        return "user/update";
    }

}
