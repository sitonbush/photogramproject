package com.cos.photogramstart.web.dto.auth;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data @ToString
public class SignupDto {
    @Size(min=3, max=20)
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String name;

    public User toEntity(){
        return  User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .build();
        //DTO가 가지고 있는 username, password, enmail, name 데이터를 기반으로한 User객체가 생성됨/
    }
}
