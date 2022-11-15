package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private static final Logger log= LoggerFactory.getLogger(AuthController.class);
    final AuthService authService;

    //로그인 페이지로 이동
    @GetMapping("/auth/signin")
    public String signinForm(){
        return "/auth/signin";
    }

    //회원가입 페이지로 이동
    @GetMapping("/auth/signup")
    public String signupForm(){
        return"/auth/signup";
    }

    //회원가입기능
    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            Map<String,String> errorMap= new HashMap<>();

            for(FieldError error:bindingResult.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
                System.out.println("*********error = " + error.getDefaultMessage());
            }
        }

        User user= signupDto.toEntity();
        User userEntity = authService.회원가입(user);
        log.info(signupDto.toString());
        System.out.println(userEntity);
        return "/auth/signin"; //회원가입이 완료되면 로그인 페이지로 이동함.
    }

}
