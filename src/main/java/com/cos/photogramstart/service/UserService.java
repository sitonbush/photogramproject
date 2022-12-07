package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserProfileDto 회원프로필(int pageUserId, int principalId){
        UserProfileDto dto = new UserProfileDto();

        User userEntity= userRepository.findById(pageUserId).orElseThrow(()-> {
            throw new CustomException("해당프로필 페이지는 없는 페이지 입니다.");
        });
        dto.setImageCount(userEntity.getImages().size());
        dto.setUser(userEntity);
        dto.setPageOwnerState(pageUserId==principalId);
        int subscribeState =subscribeRepository.mSubscribeState(principalId,pageUserId);
        int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);

        dto.setSubscribeCount(subscribeCount);
        dto.setSubscribeState(subscribeState==1);

        return dto;
    }




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
