package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {
    @NotBlank
    private String name; //필수
    @NotBlank
    private String password; //필수
    private String website;//필수가 아닌 값
    private String bio;
    private String phone;
    private String gender;


    //코드 수정이 추후 필요함/
    public User toEntity(){
        return  User.builder()
                .name(name)
                .password(password) //패스워드를 기재안했으면, 문제->validation 체크
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
