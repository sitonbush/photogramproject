package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    public User 회원수정(int id, User user){
        //1.영속화
        User userEntity= userRepository.findById(id).orElseThrow( ()->{
            return  new CustomValidationApiException("찾을 수 없는 ID 입니다.");
        });
        userEntity.setName(user.getName());

        String rawPassword= user.getPassword();
        String encPassword= bCryptPasswordEncoder.encode(rawPassword);

        userEntity.setPassword(encPassword);
        userEntity.setBio(user.getBio());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setRole(user.getRole());
        //2. 영속화된 오브젝트 수정- 더티체킹(업데이트완료)


        return userEntity;
    }
}
